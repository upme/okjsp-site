/*
 * Created on 2003. 5. 6.
 *
 */
package kr.pe.okjsp.util;

import junit.framework.TestCase;
import kr.pe.okjsp.util.CommonUtil;

/**
 * @author kenu
 *
 */
public class CommonUtilTest extends TestCase {

	/**
	 * Constructor for CommonUtilTest.
	 * @param arg0
	 */
	public CommonUtilTest(String arg0) {
		super(arg0);
	}

	public void testA2k() {
	}

	public void testK2a() {
	}

	public void testFormatDate() {
	}

	public void testShowHtml() {
	}

	/*
	 * Test for String nchk(String)
	 */
	public void testNchkString() {
	}

	/*
	 * Test for String nchk(String, String)
	 */
	public void testNchkStringString() {
	}

	public void testRplc() {
	}

	public void testCrop() {
	}

	public void testCropByte() {
	}

	/*
	 * Test for void setCookie(HttpServletResponse, String, String, int)
	 */
	public void testSetCookieHttpServletResponseStringStringint() {
	}

	/*
	 * Test for void setCookie(HttpServletResponse, String, String)
	 */
	public void testSetCookieHttpServletResponseStringString() {
	}

	public void testGetCookie() {
	}

	public void testRemoveTag() {
		String test = "&lt;html>okjsp&lt;/html>";
		assertEquals("okjsp", CommonUtil.removeTag(test));
	}
	
	public void testGetNumberedSeq() {
		assertEquals(1234, getNumberedSeq("/1234"));
		assertEquals(1234, getNumberedSeq("/1234?"));
		assertEquals(1234, getNumberedSeq("/1234?567"));
	}
	public long getNumberedSeq(String pathInfo) {
		
		long seq = 0;
		try {
			seq = Long.parseLong(pathInfo.substring(1));
		} catch (NumberFormatException e) {
			int length = pathInfo.length();
			for (int i = 1; i < length; i++) {
				char charAt = pathInfo.charAt(i);
				if (charAt >= '0' && charAt <= '9') {
					seq = seq*10 + (charAt - '0');
				} else {
					break;
				}
			}
		}
		return seq;
	}

}
