package io.github.onlynight.exchange.typed.text.plugin.sdk;

import io.github.onlynight.exchange.plugin.sdk.BaseTranslatorPlugin;
import io.github.onlynight.exchange.plugin.sdk.TranslatorHandler;
import io.github.onlynight.exchange.typed.text.plugin.sdk.doc.DocumentChText;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @param <Handler> You will set the final class TranslatorHandler in this translator.
 */
public abstract class ChTextTranslator<Handler extends TranslatorHandler> extends BaseTranslatorPlugin<Handler> {

	public static final String TEXT_TYPE_CH_TEXT = "ch-text";

	public static final String PATH = "plugins/sdk/ch-text";

	/**
	 * translate result path
	 *
	 * @param destLanguage
	 * @return
	 */
	@Override
	protected String getValuesFolderName(String destLanguage) {
		return "translated";
	}

	/**
	 * translate text type, it will set int translate_config.ini file
	 * [text type]
	 * textType = ch-text
	 *
	 * @return
	 */
	@Override
	public String textType() {
		return TEXT_TYPE_CH_TEXT;
	}

	/**
	 * the plugin will install path, it will use to load config file.
	 * And you should put the plugin and config file in it.
	 *
	 * @return
	 */
	@Override
	public String getPluginRelativePath() {
		return PATH;
	}

	/**
	 * translate access point.
	 *
	 * in this method you should use
	 * {@link io.github.onlynight.exchange.plugin.sdk.TranslatorPlugin#innerTranslate(String, String, String)}
	 * to translate text,
	 *
	 * @param srcLanguage  source language
	 * @param destLanguage destination language
	 */
	@Override
	public void translate(String srcLanguage, String destLanguage) {
		File file = new File(translatePath);
		File[] files = file.listFiles();
		if (files != null) {
			for (File docFile : files) {
				if (docFile.isFile()) {
					translate(new DocumentChText(docFile.getAbsolutePath()).parse(),
							srcLanguage, destLanguage);
				}
			}
		}
	}

	private void translate(DocumentChText document, String srcLanguage, String destLanguage) {
		List<String> sentences = document.getSentences();
		List<String> result = new ArrayList<>();
		if (sentences != null) {
			for (String sentence : sentences) {
				// use inner translator to translate text.
				String translated = innerTranslate(sentence, srcLanguage, destLanguage);
				System.out.println(sentence + " TRANSLATE TO ===> " + translated);
				result.add(translated);
			}
		}
		File srcDocFile = new File(document.getPath());
		File translateOutPath = new File(srcDocFile.getParent(), getValuesFolderName(destLanguage));
		if (!translateOutPath.exists()) {
			translateOutPath.mkdirs();
		}
		String finalPath = new File(translateOutPath, srcDocFile.getName()).getAbsolutePath();
		new DocumentChText(finalPath, result).writeDocument();
	}

}
