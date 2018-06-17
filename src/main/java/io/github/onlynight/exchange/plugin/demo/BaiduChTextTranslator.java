package io.github.onlynight.exchange.plugin.demo;

import io.github.onlynight.exchange.translator.baidu.BaiduTranslatorHandler;
import io.github.onlynight.exchange.typed.text.plugin.sdk.ChTextTranslator;

/**
 * this class just do nothing,
 * but set the {@link ChTextTranslator}'s {@link TranslatorHandler} class.
 * The {@link io.github.onlynight.exchange.plugin.sdk.BaseTranslatorPlugin} class
 * will create Handler and use it.
 */
public class BaiduChTextTranslator extends ChTextTranslator<BaiduTranslatorHandler> {
}
