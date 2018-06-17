package io.github.onlynight.exchange.typed.text.plugin.sdk.doc;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Chinese text document parse class
 */
public class DocumentChText {

	// parsed each sentence.
	private List<String> sentences = new ArrayList<>();

	private String path;

	public DocumentChText(String path) {
		this.path = path;
	}

	/**
	 * create document with sentences, and specific the result path.
	 *
	 * @param path      result path
	 * @param sentences sentences
	 */
	public DocumentChText(String path, List<String> sentences) {
		this.sentences = sentences;
		this.path = path;
	}

	public DocumentChText parse() {
		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(path)), "utf-8"));
			String line;
			StringBuilder sb = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			reader.close();
			String[] res = sb.toString().split("。");
			for (int i = 0; i < res.length; i++) {
				res[i] += "。";
			}
			sentences = Arrays.asList(res);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return this;
	}

	public void writeDocument() {
		try {
			FileOutputStream fos = new FileOutputStream(new File(path));
			for (String sentence : sentences) {
				fos.write(sentence.getBytes());
			}
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<String> getSentences() {
		return sentences;
	}

	public String getPath() {
		return path;
	}
}
