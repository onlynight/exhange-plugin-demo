package io.github.onlynight.exchange.typed.text.plugin.sdk.doc;

import org.junit.Test;

import java.io.File;

public class DocumentChTextTest {

	@Test
	public void testLoadDocument() {
		DocumentChText doc = new DocumentChText(new File(new File("").getAbsolutePath(),
				"ChText" + File.separator + "中文.txt").getAbsolutePath()).parse();
		System.out.println(doc.getSentences().size());
	}

}