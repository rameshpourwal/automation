package com.framework.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

import com.framework.manager.DriverManager;

import io.qameta.allure.Attachment;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

/**
 * @author rampourw
 *
 */
public class CaptureScreenShot {

	@Attachment(value = "page screenshot")
	public byte[] getScreenShot() {
		Screenshot s = new AShot().takeScreenshot(DriverManager.getWebDriver());

		try {
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			ImageIO.write(s.getImage(), "png", stream);
			stream.flush();
			byte[] image = stream.toByteArray();
			stream.close();
			return image;
		} catch (IOException e) {

			return e.getMessage().getBytes();
		}
	}

}
