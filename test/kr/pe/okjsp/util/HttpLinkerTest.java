package kr.pe.okjsp.util;


import junit.framework.TestCase;

public class HttpLinkerTest extends TestCase {
	public void testGetLinkedSource() {
		String source = "http://www.okjsp.pe.kr/bbs?act=VIEW&bbs=TOOL&seq=55492";
		String output = "<a href=\"http://www.okjsp.pe.kr/bbs?act=VIEW&bbs=TOOL&seq=55492\" target=\"_blank\">http://www.okjsp.pe.kr/bbs?act=VIEW&bbs=TOOL&seq=55492</a>";
		assertEquals(output, HttpLinker.getLinkedSource(source));
	}
	public void testGetLinkedHttps() {
		String source = "https://www.okjsp.pe.kr/bbs?act=VIEW&bbs=TOOL&seq=55492";
		String output = "<a href=\"https://www.okjsp.pe.kr/bbs?act=VIEW&bbs=TOOL&seq=55492\" target=\"_blank\">https://www.okjsp.pe.kr/bbs?act=VIEW&bbs=TOOL&seq=55492</a>";
		assertEquals(output, HttpLinker.getLinkedSource(source));
	}

	public void testGetLinkedSourceMixed() {
		String source = "http://www.homezigi.net 여기다 물어보시는게 빠를것같습니다 -.- ";
		String output = "<a href=\"http://www.homezigi.net\" target=\"_blank\">http://www.homezigi.net</a> 여기다 물어보시는게 빠를것같습니다 -.- ";
		assertEquals(output, HttpLinker.getLinkedSource(source));
	}
	
	public void testGetLinkedSourceMixedTrim() {
		String source = "http://www.nanasb.co.kr\n  \n  설리와 함께 놀기";
		String output = "<a href=\"http://www.nanasb.co.kr\" target=\"_blank\">http://www.nanasb.co.kr</a>\n  \n  설리와 함께 놀기";
		assertEquals(output, HttpLinker.getLinkedSource(source));
	}

	public void testGetLinkedSourceMixedLong() {
		String source = "web.xml파일이 제대로 기술이 안된듯합니다. \n<error-page> \n<error-code>404</error-code> \n"
				+ "<location>404.html</location> \n</error-page> \n아니면 \n<error-page> \n"
				+ "<exception-type>NullPointerException</exception-type> \n<location>exception.html</location> \n</error-page> \n\n"
				+ "이런식으로 기술이 될텐데.. \n에러메세지를 보니 location만 써있는듯합니다. \nhttp://java.sun.com/j2ee/dtds/web-app_2_2.dtd \n"
				+ "이곳의 dtd문서를 참조하시면 될듯합니다...;;; \n";
		String output = "web.xml파일이 제대로 기술이 안된듯합니다. \n<error-page> \n<error-code>404</error-code> \n"
				+ "<location>404.html</location> \n</error-page> \n아니면 \n<error-page> \n"
				+ "<exception-type>NullPointerException</exception-type> \n<location>exception.html</location> \n</error-page> \n\n"
				+ "이런식으로 기술이 될텐데.. \n에러메세지를 보니 location만 써있는듯합니다. \n<a href=\"http://java.sun.com/j2ee/dtds/web-app_2_2.dtd\" target=\"_blank\">http://java.sun.com/j2ee/dtds/web-app_2_2.dtd</a> \n"
				+ "이곳의 dtd문서를 참조하시면 될듯합니다...;;; \n";
		assertEquals(output, HttpLinker.getLinkedSource(source));
	}

	public void testMultiLinks() {
		String source = "그때그때 다른거 아시죠? ^^ \n일반적인 원리에 대해서는 아래 글들을 읽어보시면 도움이 되실 것 같습니다. \n\n"
				+ "장애진단(Problem Determination) 절차 \nhttp://www.javaservice.net/~java/bbs/read.cgi?m=resource&b=consult&c=r_p&n=1023729776 \n"
				+ "성능저하시, 모니터링 방법 \nhttp://www.javaservice.net/~java/bbs/read.cgi?m=appserver&b=weblogic&c=r_p&n=1012075146 \n"
				+ "RE: netstat & lsof \nhttp://www.javaservice.net/~java/bbs/read.cgi?m=appserver&b=engine&c=r_p&n=1007110331 \n"
				+ "대량데이타 조회시 고려사항 \nhttp://www.javaservice.net/~java/bbs/read.cgi?m=qna&b=consult&c=r_p&n=1000186035  ";
		String output = "그때그때 다른거 아시죠? ^^ \n일반적인 원리에 대해서는 아래 글들을 읽어보시면 도움이 되실 것 같습니다. \n\n"
				+ "장애진단(Problem Determination) 절차 \n<a href=\"http://www.javaservice.net/~java/bbs/read.cgi?m=resource&b=consult&c=r_p&n=1023729776\" target=\"_blank\">http://www.javaservice.net/~java/bbs/read.cgi?m=resource&b=consult&c=r_p&n=1023729776</a> \n"
				+ "성능저하시, 모니터링 방법 \n<a href=\"http://www.javaservice.net/~java/bbs/read.cgi?m=appserver&b=weblogic&c=r_p&n=1012075146\" target=\"_blank\">http://www.javaservice.net/~java/bbs/read.cgi?m=appserver&b=weblogic&c=r_p&n=1012075146</a> \n"
				+ "RE: netstat & lsof \n<a href=\"http://www.javaservice.net/~java/bbs/read.cgi?m=appserver&b=engine&c=r_p&n=1007110331\" target=\"_blank\">http://www.javaservice.net/~java/bbs/read.cgi?m=appserver&b=engine&c=r_p&n=1007110331</a> \n"
				+ "대량데이타 조회시 고려사항 \n<a href=\"http://www.javaservice.net/~java/bbs/read.cgi?m=qna&b=consult&c=r_p&n=1000186035\" target=\"_blank\">http://www.javaservice.net/~java/bbs/read.cgi?m=qna&b=consult&c=r_p&n=1000186035</a>  ";
		assertEquals(output, HttpLinker.getLinkedSource(source));
	}

	public void testGetLinkedSourceNoLink() {
		String source = "여기다 물어보시는게 빠를것같습니다 -.- ";
		assertEquals(source, HttpLinker.getLinkedSource(source));
	}

	public void testEndLine() {
		String source = "http://forum.java.sun.com/thread.jspa?threadID=588924&messageID=3231814<br>\r참고하세요.<br>\r"
				+ "또는 http://www.google.co.kr/search?hl=ko&q=Context+startup+failed+&lr= ";
		String output = "<a href=\"http://forum.java.sun.com/thread.jspa?threadID=588924&messageID=3231814\" target=\"_blank\">http://forum.java.sun.com/thread.jspa?threadID=588924&messageID=3231814</a><br>\r참고하세요.<br>\r"
				+ "또는 <a href=\"http://www.google.co.kr/search?hl=ko&q=Context+startup+failed+&lr=\" target=\"_blank\">http://www.google.co.kr/search?hl=ko&q=Context+startup+failed+&lr=</a> ";
		assertEquals(output, HttpLinker.getLinkedSource(source));
	}
	public void testEndLineTag() {
		String source = "http://www.hani.co.kr/arti/economy/consumer/466097.html<br><br>";
		String output = "<a href=\"http://www.hani.co.kr/arti/economy/consumer/466097.html\" target=\"_blank\">http://www.hani.co.kr/arti/economy/consumer/466097.html</a><br><br>";
		assertEquals(output, HttpLinker.getLinkedSource(source));
	}
	
	public void testLongLine() {
		String source = "<tr><td width=\"15%\" class=\"td\">\n세바리 <br/>\n<sup>221.148.20.149</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n아직까지도 윈도랑 AMD랑은 그다지 안친한거 같습니다. 저도 비슷한 사양인데 \n\n<br>(AMD 64 3000+ 뉴캐슬, ASUS K8N) 가끔 특정 프로그램에서 회사컴(인텔 P4 2.4)보다도 \n<br>왠지 느린 체감성능을 보입니다. 가끔 cpu 100%를 잡아먹지를 않나...\n<br>\n<br>결코 떨어지는 cpu가 아닌데 개인사용자 OS는 응당 윈도라는 뿌리깊은 의존성이 사라지지않는한... AMD는 선택에 있어 일단 한번 머뭇거리는 cpu일수밖에 없더군요.\n<br> \n2005-06-24 17:48:39.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86198')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\nZeroidle <br/>\n<sup>218.50.116.85</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n\n<div class=\"wrap\">\nAMD매니아 입니다.\n<br>\n<br>네모선장님과 세바리님 말씀에 동의할 수 없습니다^^;\n<br>\n<br>다만 아직까지 Windows 2003 x86-64bit의 안정성에 문제가 많다는 얘기가 있습니다.\n<br>\n<br>네모선장님께서는 Windows 2003 x86-32bit로 인스톨 하시기 바랍니다;;\n<br>\n<br>세바리님 말씀중에 cpu 100%얘기는 아마 웜이나 기타 프로그램 충돌로 보입니다.\n<br>\n<br>현재 AMD 2200~ 64bit 3000+ 까지 사용하고 있지만 전혀 문제가 없습니다.\n<br>\n<br>이 글로 인해 AMD를 생각하시다가 Intel로 돌아서시는 분이 없으시길 바랍니다.\n<br> \n2005-06-24 19:03:10.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86201')\"><font color=\"red\">x</font></a>\n\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\njango <br/>\n<sup>219.163.188.189</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n허걱 어떻게하죠..저두 며칠전 AMD64  (뉴캐슬)2800+ 루 했는데요..보드는 다른 회사지만요.\n<br>지금 XP깔구 쓰는데...2000으로 바꿀까 생각중인데요. 저에게도 그런 괴현상이 일어나면 \n<br>어떻게하죠....무서버라...\n<br> \n2005-06-24 19:07:51.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86202')\"><font color=\"red\">x</font></a>\n\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\nZeroidle <br/>\n<sup>218.50.116.85</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n아... 우려했던일이 -0-\n<br>\n<br>저는 영등포 양평에서 일하고있습니다.\n<br>\n<br>주말에 가까운곳에 계시면 저에게 연락해 주세요..\n<br>\n<br>날아가서 시스템을 재설치 해드리겠습니다 ㅡ.ㅡ;; \n2005-06-24 19:24:19.0\n\n<a href=\"javascript:;\" onclick=\"show_memodel('86204')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\nZeroidle <br/>\n<sup>218.50.116.85</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n네모선장님 Windows Server 2003 Ent - x86을 설치해보세요 -0-\n<br>\n<br>서울이시라면.. 연락주시면.. 제가 직접 설치해 드리겠습니다.\n<br>\n<br>저번에 메인보드 물려받은 보답도 할겸.. 연락주세요^^;\n\n<br>\n<br>0.1　1 ~ 9 3_7　8－0 4_3=4 입니다 -.- \n2005-06-24 19:26:40.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86205')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\n네모선장 <br/>\n<sup>222.105.152.71</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\nㅠㅠ 다해봤습니다.... \n<br>절대뻥아닙니다. 오늘 하루동안 설치해본 OS들 \n<br>Windows \n\n<br>windows xp pro / home 32bit \n<br>windows xp 64bit beta \n<br>windows 2000 server / AD \n<br>windows 2003 EN/SN \n<br>windows 2003 64bit \n<br>windows 98se \n<br>\n<br>윈도우 계열은 하나두 안됩니다....\n<br>Linux \n<br>fedora core2 64bit \n<br>fedora core2 \n<br>centOS4 \n<br>redhat linux AS 3 \n<br>redhat linux 9 \n<br>\n<br>메모리교체.... VGA교체.... 파워교체... 하드교체.... \n<br>(333/삼성 256(s) x 2) (지포스2MX / 리바TNT64 / 지포스4MX) (시게이트40G/웨스턴디스털80G \n\n<br>후지쯔20G) \n<br>\n<br>결국 안되더군요... 메인보드 회사에 문의해봤습니다. 해답은 윈트로닉스에 다시 A/S문의 해보라구합니다. 자기내 CPU로는 제대로 잘된다구하는데.. ㅠ_ㅠ 할말없더군요...결국 조금전에 윈트로닉스에 문의메일 보냈습니다. ㅠ_ㅠ \n<br>생각해주셔서 감사합니다. ㅠㅠ 함 CPU테스트 받아봐야할듯하네요 \n<br> <br> \n2005-06-24 19:30:04.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86206')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\n네모선장 <br/>\n<sup>222.105.152.71</sup>\n</td>\n\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n제로아이들님 생각해주셔서 감사합니다 ㅠ_ㅠ 제가 지방이라서 (전주....) 라서요\n<br> \n2005-06-24 19:39:28.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86207')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\npainworld <br/>\n<sup>218.153.39.165</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n\n헉 저도 집이 전주인데 ^^; \n2005-06-24 21:50:21.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86209')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\n흠.. <br/>\n<sup>211.49.43.88</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n헉~ 하루만에 그많은 OS를...i0i\n<br>그런데 저희 회사분도 AMD64를 사용한다고 하는데요..정확한 모델은 모르지만..\n<br>아무일없이 게임도 잘하고 사용도 좋다고 하더군요. CPU 교체해달라고 때를 쓰심이..ㅋㅋㅋ \n2005-06-24 23:18:44.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86212')\"><font color=\"red\">x</font></a>\n\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\nmoonumi <br/>\n<sup>211.110.131.216</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n저도 AMD제품을 써보기는 했는데,\n<br>불안정한 면이 아직은 있지 않나 싶습니다.\n<br>저도 가끔 컴터가 아예멈출정도로 100%를 먹을때가 있고, (저 세팅 잘합니다만...)\n<br>정말 이해하기 어려운 에러를 볼때도 있는데...\n<br>인텔보다 더 빈번한것 같습니다.\n<br>순전히 개인적인 의견입니다. \n2005-06-25 00:59:28.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86215')\"><font color=\"red\">x</font></a>\n\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\npistos <br/>\n<sup>218.152.156.91</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n싼맛에 AMD 쓴지 여러해인데.. \n<br>보드칩셋(특히 via. ㅠㅠ) 드라이버의 불안정(?) 으로 인한 문제를 제외하면..\n<br>nforce 계열로 넘어온 이후로는 별탈없이 잘 쓰고 있습니다.. ^^\n<br> \n2005-06-25 02:19:15.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86223')\"><font color=\"red\">x</font></a>\n\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\n네모 <br/>\n<sup>61.253.101.163</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n정말 짜증나셨겠네요. 그래도 저 많은 걸 설치하시면서 인내심이 대단하신것 같습니다.^^;\n<br>전 그래도 담에 AMD를 써보아야겠어요.^^ \n2005-06-25 10:16:57.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86227')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\n\nZeroidle <br/>\n<sup>218.50.116.85</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n아.. 네모선장님 댓글보니 생각이 문득드네요\n<br>AMD64쓰시면서 메모리가 DDR333이시라구요?\n<br>\n<br>Athlon Barton 및 셈프론 등등은 CPU동작클럭이 166Mhz이기 땜시 DDR333을 사용하시면 되지만\n<br>\n<br>AMD64이시면 CPU동작클럭이 200Mhz입니다. DDR400Mhz으로 구성하셔서 CPU와 램을 동기화시켜주는게 좋지않나 하는 생각이 듭니다.\n<br>\n<br>또 윈도우즈 서버군에서 블루스크린은 대부분 호환되지않는 프로그램 및 비정상적인 메모리때문에 그런경우가 대부분입니다.\n<br>\n<br>감사합니다. \n2005-06-25 10:27:45.0\n\n<a href=\"javascript:;\" onclick=\"show_memodel('86229')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\nZeroidle <br/>\n<sup>218.50.116.85</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n헛 고객지원인줄 알고 감사합니다라니 -.- \n2005-06-25 10:28:31.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86230')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\n네모선장 <br/>\n<sup>222.105.153.45</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n^^ 좋은 답변 감사합니다. ㅋㅋ 400짜리 메모리는 하나두 없어서리... 전부 333 /266 이것밖에 없어서 테스트 못해봐서요 메모리하나... 사야하나.... ㅠ_ㅠ  지금 열받아서... 안에 있는 부품 다빼버리구 한쪽에다 처박아 놨습니다. 다시 예전 컴퓨터를 사용하네요 애슬론(써드)1800+ .... 밤새우며 OS설치했다 지웠다 오류봤다가... 정신없네요 결국포기했습니다...\n<br> 데비안..초기 화면설치 화면에서 검은 화면에서 동작이 안됩니다.\n<br> centOS 알수없는 커널페닉 상태의 메시지가 난무...\n<br> (메모리.. 위치... 다른메모리.. 하드..VGA.. 교체...Cmos에서 온보드제품 제거등등...\n<br>  똑같은결과...)\n\n<br> Redhat Linux EN AS 4 이상하게 잘됩니다.... 아무문제없이 몇시간 이상 켜놔도... \n<br> Redhat Linux EN AS 3 마찬가지로 잘되고요 ^^;\n<br> fedora core 2 64  설치잘되고 작동잘되고 ㅋㅋ\n<br> fedora core 2 마찬가지구요...\n<br>  <br> 롱혼--> 왜... 재부팅이 되지....\n<br> windows 2003 64bit 데비안 과같은결과 먹통... ㅋㅋ\n<br> windows 2003 EN 요거 하나 설치되는데..  시스템의 알수없는 오류...설치내내 팝업...\n<br> (하드... 메모리... 등등 교체해보거나 bios업데이트및 셋업해도 마찬가지결과...)\n\n<br> windows 2000 / XP /98 아예 블루스크린으로 인한 설치및 진행불가...\n<br>나중에 도저히 안되면... Redhat전용 이나 쓰려구 하하하하... ㅠ_ㅠ\n<br>(문의 메일 보내두 답변두없구.... )\n<br> <br> <br> <br>  \n2005-06-25 10:44:20.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86232')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\nZeroidle <br/>\n<sup>218.50.116.85</sup>\n\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\nPC 택배로 보내주시면 Windows Server 2003 Ent SP1 Volume Version\n<br>\n<br>설치해서 보내드리겠습니다.\n<br>\n<br>어차피 옆에 쳐박아 놓으셨다니까 보내주세요\n<br>\n<br>서울시 영등포구 양평동 247번지 기계상가 2동 302호 마루인터넷\n<br>\n<br>제 이름하고 전화번호는 아시죠? ^^ 보내주세요..\n<br> \n2005-06-25 11:17:49.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86233')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\n네버다이 <br/>\n<sup>61.81.118.215</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n여건이 된다면 메인보드 다른 회사껄로 바꿔보면 좋을거 같은데요.\n<br> \n2005-06-25 11:24:33.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86234')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\n\n네모선장 <br/>\n<sup>222.105.153.45</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n^^ 제로아이들님의 성원에 정말 진심으로 감사드립니다. 하하.. 해결?營윱求?. 그나마 기분 조금 풀어집니다. 앞집 동생이 저와비슷한사양의 컴(제가 조립해주었거든요 같은 동종 시피유에 메인보드는 아수스제품) 테스트결과 이상없이 잘되고 잘설치 됩니다. 오류등등 없더군요 답은하나 메인보드 이상인것 같습니다. 다시금 문의 메일 보내야할것같네요 ㅋㅋ 안심 Cpu이상이면 정말 눈물날뻔..  \n2005-06-25 11:39:32.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86235')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\n이희승 <br/>\n<sup>211.171.214.27</sup>\n\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\nAMD 는 메인보드나 메인보드 칩셋을 좀 잘 타죠..^^\n<br>저는 여기 저기 알아 보고 AMD64 3200+ 로 몇달 전에 구입했는데 전혀 이상없이 잘 쓰고 있네요..  근데 사실 게임은 거의 안해서 다운될 일도 없기는 하네요 ㅎㅎ-_-; \n2005-06-25 13:12:01.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86237')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\n네모 <br/>\n<sup>61.253.101.163</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n\n네모선장님 보드랑 관련해서 악연이 많은가봅니다^^;;\n<br> \n2005-06-26 00:45:50.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86250')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\nZeroidle <br/>\n<sup>218.50.126.151</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n그런거 같네요^^ 네모선장님께 메인보드 분양받았는데..\n<br>\n\n<br>아무런 이상없이 잘 쓰고 있는데요 ㅎㅎ \n2005-06-26 10:16:11.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86255')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\n네모선장 <br/>\n<sup>222.105.153.200</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\nㅠ_ㅠ 눈물납니다... 웬만하면 컴하나 장만하면 업그레이드등등 기타 비용거의 안들어가는데 이놈의 AMD64는 작년에사서 택배비 등등... 비용 많이 들어갔네요 흑흑....  네모님 말씀데로 AMD64하고는 악연인가봅니다.. (에궁 눈물나라.. 남들 잘사용할때 왜 나의 손길만 타면 이상해지는지... ) \n2005-06-26 21:39:44.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86278')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\niceman2k <br/>\n<sup>168.154.227.19</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n음.. 저도 AMD는 비추. 왠지 유리공예품 같은 느낌이라 막굴리면 뽀개질듯해서 무서워요 -_-; \n2005-06-27 09:02:56.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86283')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\n보라비 <br/>\n\n<sup>61.73.70.130</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n저는 AMD 문제없이 잘 쓰고 있습니다. 애슬론xp 바톤2500 쓰다가 이번에 amd64 윈체스터 3000으로 바꿨는데요. 보드는 chaintech인가?? 하여튼 잘 돌아가던데...\n<br>빨리 원상복구 되시길 바랄께요.. ^^ \n2005-06-27 15:15:47.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86355')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\n단쿠카 <br/>\n<sup>203.247.145.53</sup>\n</td>\n\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n보라비 님이 저랑 같은 세팅이군요. 저도 64윈체3000에 체인텍 쓰는데, 별 무리 없이 돌아가고 있습니다. 당나귀 켜놓고, 와우 창모드로 돌리면서, TV on Air를 항상위 모드로 구석탱이에 놓고 봐도 무리가 없이 돌아가더라구요  \n2005-06-27 15:21:11.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86356')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\njango <br/>\n<sup>219.163.188.189</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\namd 64bit 2800 정도면 인텔의 어느정도 수준의 cpu에 해당하죠?\n<br>벤치마킹돌아다니면..amd xp에 대해선 나왔던데...\n\n<br>xp 3000 과 inter 3.06Mhz 비교하니 amd가 거의 절반 수준의 성능을 가지고 있던데...\n<br>(그곳 벤치마킹에서 amd 64bit의 출시를 기대한다고 하면서 말을 맺었는데...꽤 지난 기사죠.)\n<br>\n<br>64bit에선 어떠한 결과가 나올지 궁금하네요. \n2005-06-27 22:08:26.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86405')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\nZeroidle <br/>\n<sup>218.232.9.58</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n어떻게 측정하냐에 따라 다릅니다. 인텔의 경우 400/533/800의 FSB를 가지고 있었지만\n\n<br>\n<br>AMD는 고작 333이었죠(Barton까지요) AMD64도 800의 FSB를 가지고있고\n<br>\n<br>ab(ApacheBenchmark)로 테스트 해보니 AMD64 2800+이 Intel 2.8 HT지원보다 낫더군요\n<br>\n<br>ab -c50000 -n1000 http://localhost:80/index.jsp\n<br>\n<br>초당 1ms차이이지만 모이면 어마어마합니다 -.-\n<br>\n<br>저는 AMD Athlon XP Barton 2800+을 쓰고있는데요 체감속도는 Intel 2.8 HT보다 낫습니다.\n<br>\n<br>어떤 비교였었는지 궁금하네요^^; \n2005-06-27 22:49:43.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86406')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\n\nKenny <br/>\n<sup>211.218.14.184</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\nAMD XP 3000 과 Intel 3.06Mhz 를 비교하였을때 AMD가 절반 수준의 성능을 내 주는 벤치는 동영상 인코딩 벤치 밖에 없습니다.\n<br>Intel의 HT는, CPU에 100%의 부하가 걸렸을 때 비정상적인 동작을 보여줄 수 있습니다. HyperThreading 이라는 구조 자체의 문제로 알고 있습니다. 자세한 사항은 파코즈의 듀얼 CPU 분석에서 보시면 어떤 문제인지 아실 수 있으실 겁니다.\n<br>전반적으로, HT라는 불안정한 기술(IBM의 PowerPC G5에 들어가 있는 비슷한 기술과 비교해 봤을때, 약간 과도기적인 기술이라는 견해가 지배적입니다. 물론, 대용량 데이터를 다루는 서버 시장에서의 견해이지만요. 일반적인 환경에서는 큰 차이가 없을 겁니다.)\n<br>\n<br>Intel 구조에서는 L1캐쉬 보다는 L2캐쉬의 크기가 성능에 영향을 상대적으로 많이 줍니다.\n<br>AMD 에서는 L1캐쉬의 크기가 L2캐쉬의 크기보다 성능에 영향을 상대적으로 많이 줍니다.\n<br>\n<br>두 CPU의 구조 자체가 많이 다르기 때문에, 어떤게 더 좋다.. 라고 말하기는 좀 그렇네요.\n<br>\n<br>다만, 개인적인 경험상, 벤치마크 등의 절대적인 수치 지표는 Intel쪽이 좀 더 높게 나오고, 실제 체감으로는 AMD 쪽이 좀 더 빠르다고 느껴지네요.\n\n<br>\n<br>일반적으로 멀티태스킹에 Intel쪽이 강하다고 하는데, 실 사용에서는 그렇게 하드코어한 멀티태스킹(와우하는데 와우의 배경음악을 꺼 놓고 별도의 MP3를 재생한다던가, 마야나 맥스로 랜더링 걸어 놓고 MP3 재생 혹은 동영상 인코딩을 한다던가...)을 잘 안하기 때문에, (익스 열댓개 열려 있고, 이클립스 떠 있고, 비주얼 스튜디오 떠있는 정도? CPU에 직접적으로 부하가 계속 걸리지는 않죠. 몇몇 잘못 제작된 플래시를 제외하면) 별 차이 없을 겁니다. \n2005-06-28 14:54:43.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86473')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n";
		String output = "<tr><td width=\"15%\" class=\"td\">\n세바리 <br/>\n<sup>221.148.20.149</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n아직까지도 윈도랑 AMD랑은 그다지 안친한거 같습니다. 저도 비슷한 사양인데 \n\n<br>(AMD 64 3000+ 뉴캐슬, ASUS K8N) 가끔 특정 프로그램에서 회사컴(인텔 P4 2.4)보다도 \n<br>왠지 느린 체감성능을 보입니다. 가끔 cpu 100%를 잡아먹지를 않나...\n<br>\n<br>결코 떨어지는 cpu가 아닌데 개인사용자 OS는 응당 윈도라는 뿌리깊은 의존성이 사라지지않는한... AMD는 선택에 있어 일단 한번 머뭇거리는 cpu일수밖에 없더군요.\n<br> \n2005-06-24 17:48:39.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86198')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\nZeroidle <br/>\n<sup>218.50.116.85</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n\n<div class=\"wrap\">\nAMD매니아 입니다.\n<br>\n<br>네모선장님과 세바리님 말씀에 동의할 수 없습니다^^;\n<br>\n<br>다만 아직까지 Windows 2003 x86-64bit의 안정성에 문제가 많다는 얘기가 있습니다.\n<br>\n<br>네모선장님께서는 Windows 2003 x86-32bit로 인스톨 하시기 바랍니다;;\n<br>\n<br>세바리님 말씀중에 cpu 100%얘기는 아마 웜이나 기타 프로그램 충돌로 보입니다.\n<br>\n<br>현재 AMD 2200~ 64bit 3000+ 까지 사용하고 있지만 전혀 문제가 없습니다.\n<br>\n<br>이 글로 인해 AMD를 생각하시다가 Intel로 돌아서시는 분이 없으시길 바랍니다.\n<br> \n2005-06-24 19:03:10.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86201')\"><font color=\"red\">x</font></a>\n\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\njango <br/>\n<sup>219.163.188.189</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n허걱 어떻게하죠..저두 며칠전 AMD64  (뉴캐슬)2800+ 루 했는데요..보드는 다른 회사지만요.\n<br>지금 XP깔구 쓰는데...2000으로 바꿀까 생각중인데요. 저에게도 그런 괴현상이 일어나면 \n<br>어떻게하죠....무서버라...\n<br> \n2005-06-24 19:07:51.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86202')\"><font color=\"red\">x</font></a>\n\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\nZeroidle <br/>\n<sup>218.50.116.85</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n아... 우려했던일이 -0-\n<br>\n<br>저는 영등포 양평에서 일하고있습니다.\n<br>\n<br>주말에 가까운곳에 계시면 저에게 연락해 주세요..\n<br>\n<br>날아가서 시스템을 재설치 해드리겠습니다 ㅡ.ㅡ;; \n2005-06-24 19:24:19.0\n\n<a href=\"javascript:;\" onclick=\"show_memodel('86204')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\nZeroidle <br/>\n<sup>218.50.116.85</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n네모선장님 Windows Server 2003 Ent - x86을 설치해보세요 -0-\n<br>\n<br>서울이시라면.. 연락주시면.. 제가 직접 설치해 드리겠습니다.\n<br>\n<br>저번에 메인보드 물려받은 보답도 할겸.. 연락주세요^^;\n\n<br>\n<br>0.1　1 ~ 9 3_7　8－0 4_3=4 입니다 -.- \n2005-06-24 19:26:40.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86205')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\n네모선장 <br/>\n<sup>222.105.152.71</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\nㅠㅠ 다해봤습니다.... \n<br>절대뻥아닙니다. 오늘 하루동안 설치해본 OS들 \n<br>Windows \n\n<br>windows xp pro / home 32bit \n<br>windows xp 64bit beta \n<br>windows 2000 server / AD \n<br>windows 2003 EN/SN \n<br>windows 2003 64bit \n<br>windows 98se \n<br>\n<br>윈도우 계열은 하나두 안됩니다....\n<br>Linux \n<br>fedora core2 64bit \n<br>fedora core2 \n<br>centOS4 \n<br>redhat linux AS 3 \n<br>redhat linux 9 \n<br>\n<br>메모리교체.... VGA교체.... 파워교체... 하드교체.... \n<br>(333/삼성 256(s) x 2) (지포스2MX / 리바TNT64 / 지포스4MX) (시게이트40G/웨스턴디스털80G \n\n<br>후지쯔20G) \n<br>\n<br>결국 안되더군요... 메인보드 회사에 문의해봤습니다. 해답은 윈트로닉스에 다시 A/S문의 해보라구합니다. 자기내 CPU로는 제대로 잘된다구하는데.. ㅠ_ㅠ 할말없더군요...결국 조금전에 윈트로닉스에 문의메일 보냈습니다. ㅠ_ㅠ \n<br>생각해주셔서 감사합니다. ㅠㅠ 함 CPU테스트 받아봐야할듯하네요 \n<br> <br> \n2005-06-24 19:30:04.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86206')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\n네모선장 <br/>\n<sup>222.105.152.71</sup>\n</td>\n\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n제로아이들님 생각해주셔서 감사합니다 ㅠ_ㅠ 제가 지방이라서 (전주....) 라서요\n<br> \n2005-06-24 19:39:28.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86207')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\npainworld <br/>\n<sup>218.153.39.165</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n\n헉 저도 집이 전주인데 ^^; \n2005-06-24 21:50:21.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86209')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\n흠.. <br/>\n<sup>211.49.43.88</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n헉~ 하루만에 그많은 OS를...i0i\n<br>그런데 저희 회사분도 AMD64를 사용한다고 하는데요..정확한 모델은 모르지만..\n<br>아무일없이 게임도 잘하고 사용도 좋다고 하더군요. CPU 교체해달라고 때를 쓰심이..ㅋㅋㅋ \n2005-06-24 23:18:44.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86212')\"><font color=\"red\">x</font></a>\n\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\nmoonumi <br/>\n<sup>211.110.131.216</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n저도 AMD제품을 써보기는 했는데,\n<br>불안정한 면이 아직은 있지 않나 싶습니다.\n<br>저도 가끔 컴터가 아예멈출정도로 100%를 먹을때가 있고, (저 세팅 잘합니다만...)\n<br>정말 이해하기 어려운 에러를 볼때도 있는데...\n<br>인텔보다 더 빈번한것 같습니다.\n<br>순전히 개인적인 의견입니다. \n2005-06-25 00:59:28.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86215')\"><font color=\"red\">x</font></a>\n\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\npistos <br/>\n<sup>218.152.156.91</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n싼맛에 AMD 쓴지 여러해인데.. \n<br>보드칩셋(특히 via. ㅠㅠ) 드라이버의 불안정(?) 으로 인한 문제를 제외하면..\n<br>nforce 계열로 넘어온 이후로는 별탈없이 잘 쓰고 있습니다.. ^^\n<br> \n2005-06-25 02:19:15.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86223')\"><font color=\"red\">x</font></a>\n\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\n네모 <br/>\n<sup>61.253.101.163</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n정말 짜증나셨겠네요. 그래도 저 많은 걸 설치하시면서 인내심이 대단하신것 같습니다.^^;\n<br>전 그래도 담에 AMD를 써보아야겠어요.^^ \n2005-06-25 10:16:57.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86227')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\n\nZeroidle <br/>\n<sup>218.50.116.85</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n아.. 네모선장님 댓글보니 생각이 문득드네요\n<br>AMD64쓰시면서 메모리가 DDR333이시라구요?\n<br>\n<br>Athlon Barton 및 셈프론 등등은 CPU동작클럭이 166Mhz이기 땜시 DDR333을 사용하시면 되지만\n<br>\n<br>AMD64이시면 CPU동작클럭이 200Mhz입니다. DDR400Mhz으로 구성하셔서 CPU와 램을 동기화시켜주는게 좋지않나 하는 생각이 듭니다.\n<br>\n<br>또 윈도우즈 서버군에서 블루스크린은 대부분 호환되지않는 프로그램 및 비정상적인 메모리때문에 그런경우가 대부분입니다.\n<br>\n<br>감사합니다. \n2005-06-25 10:27:45.0\n\n<a href=\"javascript:;\" onclick=\"show_memodel('86229')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\nZeroidle <br/>\n<sup>218.50.116.85</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n헛 고객지원인줄 알고 감사합니다라니 -.- \n2005-06-25 10:28:31.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86230')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\n네모선장 <br/>\n<sup>222.105.153.45</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n^^ 좋은 답변 감사합니다. ㅋㅋ 400짜리 메모리는 하나두 없어서리... 전부 333 /266 이것밖에 없어서 테스트 못해봐서요 메모리하나... 사야하나.... ㅠ_ㅠ  지금 열받아서... 안에 있는 부품 다빼버리구 한쪽에다 처박아 놨습니다. 다시 예전 컴퓨터를 사용하네요 애슬론(써드)1800+ .... 밤새우며 OS설치했다 지웠다 오류봤다가... 정신없네요 결국포기했습니다...\n<br> 데비안..초기 화면설치 화면에서 검은 화면에서 동작이 안됩니다.\n<br> centOS 알수없는 커널페닉 상태의 메시지가 난무...\n<br> (메모리.. 위치... 다른메모리.. 하드..VGA.. 교체...Cmos에서 온보드제품 제거등등...\n<br>  똑같은결과...)\n\n<br> Redhat Linux EN AS 4 이상하게 잘됩니다.... 아무문제없이 몇시간 이상 켜놔도... \n<br> Redhat Linux EN AS 3 마찬가지로 잘되고요 ^^;\n<br> fedora core 2 64  설치잘되고 작동잘되고 ㅋㅋ\n<br> fedora core 2 마찬가지구요...\n<br>  <br> 롱혼--> 왜... 재부팅이 되지....\n<br> windows 2003 64bit 데비안 과같은결과 먹통... ㅋㅋ\n<br> windows 2003 EN 요거 하나 설치되는데..  시스템의 알수없는 오류...설치내내 팝업...\n<br> (하드... 메모리... 등등 교체해보거나 bios업데이트및 셋업해도 마찬가지결과...)\n\n<br> windows 2000 / XP /98 아예 블루스크린으로 인한 설치및 진행불가...\n<br>나중에 도저히 안되면... Redhat전용 이나 쓰려구 하하하하... ㅠ_ㅠ\n<br>(문의 메일 보내두 답변두없구.... )\n<br> <br> <br> <br>  \n2005-06-25 10:44:20.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86232')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\nZeroidle <br/>\n<sup>218.50.116.85</sup>\n\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\nPC 택배로 보내주시면 Windows Server 2003 Ent SP1 Volume Version\n<br>\n<br>설치해서 보내드리겠습니다.\n<br>\n<br>어차피 옆에 쳐박아 놓으셨다니까 보내주세요\n<br>\n<br>서울시 영등포구 양평동 247번지 기계상가 2동 302호 마루인터넷\n<br>\n<br>제 이름하고 전화번호는 아시죠? ^^ 보내주세요..\n<br> \n2005-06-25 11:17:49.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86233')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\n네버다이 <br/>\n<sup>61.81.118.215</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n여건이 된다면 메인보드 다른 회사껄로 바꿔보면 좋을거 같은데요.\n<br> \n2005-06-25 11:24:33.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86234')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\n\n네모선장 <br/>\n<sup>222.105.153.45</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n^^ 제로아이들님의 성원에 정말 진심으로 감사드립니다. 하하.. 해결?營윱求?. 그나마 기분 조금 풀어집니다. 앞집 동생이 저와비슷한사양의 컴(제가 조립해주었거든요 같은 동종 시피유에 메인보드는 아수스제품) 테스트결과 이상없이 잘되고 잘설치 됩니다. 오류등등 없더군요 답은하나 메인보드 이상인것 같습니다. 다시금 문의 메일 보내야할것같네요 ㅋㅋ 안심 Cpu이상이면 정말 눈물날뻔..  \n2005-06-25 11:39:32.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86235')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\n이희승 <br/>\n<sup>211.171.214.27</sup>\n\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\nAMD 는 메인보드나 메인보드 칩셋을 좀 잘 타죠..^^\n<br>저는 여기 저기 알아 보고 AMD64 3200+ 로 몇달 전에 구입했는데 전혀 이상없이 잘 쓰고 있네요..  근데 사실 게임은 거의 안해서 다운될 일도 없기는 하네요 ㅎㅎ-_-; \n2005-06-25 13:12:01.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86237')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\n네모 <br/>\n<sup>61.253.101.163</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n\n네모선장님 보드랑 관련해서 악연이 많은가봅니다^^;;\n<br> \n2005-06-26 00:45:50.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86250')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\nZeroidle <br/>\n<sup>218.50.126.151</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n그런거 같네요^^ 네모선장님께 메인보드 분양받았는데..\n<br>\n\n<br>아무런 이상없이 잘 쓰고 있는데요 ㅎㅎ \n2005-06-26 10:16:11.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86255')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\n네모선장 <br/>\n<sup>222.105.153.200</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\nㅠ_ㅠ 눈물납니다... 웬만하면 컴하나 장만하면 업그레이드등등 기타 비용거의 안들어가는데 이놈의 AMD64는 작년에사서 택배비 등등... 비용 많이 들어갔네요 흑흑....  네모님 말씀데로 AMD64하고는 악연인가봅니다.. (에궁 눈물나라.. 남들 잘사용할때 왜 나의 손길만 타면 이상해지는지... ) \n2005-06-26 21:39:44.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86278')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\niceman2k <br/>\n<sup>168.154.227.19</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n음.. 저도 AMD는 비추. 왠지 유리공예품 같은 느낌이라 막굴리면 뽀개질듯해서 무서워요 -_-; \n2005-06-27 09:02:56.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86283')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\n보라비 <br/>\n\n<sup>61.73.70.130</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n저는 AMD 문제없이 잘 쓰고 있습니다. 애슬론xp 바톤2500 쓰다가 이번에 amd64 윈체스터 3000으로 바꿨는데요. 보드는 chaintech인가?? 하여튼 잘 돌아가던데...\n<br>빨리 원상복구 되시길 바랄께요.. ^^ \n2005-06-27 15:15:47.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86355')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\n단쿠카 <br/>\n<sup>203.247.145.53</sup>\n</td>\n\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n보라비 님이 저랑 같은 세팅이군요. 저도 64윈체3000에 체인텍 쓰는데, 별 무리 없이 돌아가고 있습니다. 당나귀 켜놓고, 와우 창모드로 돌리면서, TV on Air를 항상위 모드로 구석탱이에 놓고 봐도 무리가 없이 돌아가더라구요  \n2005-06-27 15:21:11.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86356')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\njango <br/>\n<sup>219.163.188.189</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\namd 64bit 2800 정도면 인텔의 어느정도 수준의 cpu에 해당하죠?\n<br>벤치마킹돌아다니면..amd xp에 대해선 나왔던데...\n\n<br>xp 3000 과 inter 3.06Mhz 비교하니 amd가 거의 절반 수준의 성능을 가지고 있던데...\n<br>(그곳 벤치마킹에서 amd 64bit의 출시를 기대한다고 하면서 말을 맺었는데...꽤 지난 기사죠.)\n<br>\n<br>64bit에선 어떠한 결과가 나올지 궁금하네요. \n2005-06-27 22:08:26.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86405')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\nZeroidle <br/>\n<sup>218.232.9.58</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\n어떻게 측정하냐에 따라 다릅니다. 인텔의 경우 400/533/800의 FSB를 가지고 있었지만\n\n<br>\n<br>AMD는 고작 333이었죠(Barton까지요) AMD64도 800의 FSB를 가지고있고\n<br>\n<br>ab(ApacheBenchmark)로 테스트 해보니 AMD64 2800+이 Intel 2.8 HT지원보다 낫더군요\n<br>\n<br>ab -c50000 -n1000 <a href=\"http://localhost:80/index.jsp\" target=\"_blank\">http://localhost:80/index.jsp</a>\n<br>\n<br>초당 1ms차이이지만 모이면 어마어마합니다 -.-\n<br>\n<br>저는 AMD Athlon XP Barton 2800+을 쓰고있는데요 체감속도는 Intel 2.8 HT보다 낫습니다.\n<br>\n<br>어떤 비교였었는지 궁금하네요^^; \n2005-06-27 22:49:43.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86406')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n<tr><td width=\"15%\" class=\"td\">\n\nKenny <br/>\n<sup>211.218.14.184</sup>\n</td>\n<td width=\"85%\" class=\"td\">\n<div class=\"wrap\">\nAMD XP 3000 과 Intel 3.06Mhz 를 비교하였을때 AMD가 절반 수준의 성능을 내 주는 벤치는 동영상 인코딩 벤치 밖에 없습니다.\n<br>Intel의 HT는, CPU에 100%의 부하가 걸렸을 때 비정상적인 동작을 보여줄 수 있습니다. HyperThreading 이라는 구조 자체의 문제로 알고 있습니다. 자세한 사항은 파코즈의 듀얼 CPU 분석에서 보시면 어떤 문제인지 아실 수 있으실 겁니다.\n<br>전반적으로, HT라는 불안정한 기술(IBM의 PowerPC G5에 들어가 있는 비슷한 기술과 비교해 봤을때, 약간 과도기적인 기술이라는 견해가 지배적입니다. 물론, 대용량 데이터를 다루는 서버 시장에서의 견해이지만요. 일반적인 환경에서는 큰 차이가 없을 겁니다.)\n<br>\n<br>Intel 구조에서는 L1캐쉬 보다는 L2캐쉬의 크기가 성능에 영향을 상대적으로 많이 줍니다.\n<br>AMD 에서는 L1캐쉬의 크기가 L2캐쉬의 크기보다 성능에 영향을 상대적으로 많이 줍니다.\n<br>\n<br>두 CPU의 구조 자체가 많이 다르기 때문에, 어떤게 더 좋다.. 라고 말하기는 좀 그렇네요.\n<br>\n<br>다만, 개인적인 경험상, 벤치마크 등의 절대적인 수치 지표는 Intel쪽이 좀 더 높게 나오고, 실제 체감으로는 AMD 쪽이 좀 더 빠르다고 느껴지네요.\n\n<br>\n<br>일반적으로 멀티태스킹에 Intel쪽이 강하다고 하는데, 실 사용에서는 그렇게 하드코어한 멀티태스킹(와우하는데 와우의 배경음악을 꺼 놓고 별도의 MP3를 재생한다던가, 마야나 맥스로 랜더링 걸어 놓고 MP3 재생 혹은 동영상 인코딩을 한다던가...)을 잘 안하기 때문에, (익스 열댓개 열려 있고, 이클립스 떠 있고, 비주얼 스튜디오 떠있는 정도? CPU에 직접적으로 부하가 계속 걸리지는 않죠. 몇몇 잘못 제작된 플래시를 제외하면) 별 차이 없을 겁니다. \n2005-06-28 14:54:43.0\n<a href=\"javascript:;\" onclick=\"show_memodel('86473')\"><font color=\"red\">x</font></a>\n</div></td></tr>\n\n";
		assertEquals(output, HttpLinker.getLinkedSource(source));
	}
	
	public void testBr() {
		String source = "이렇게 하면 되네요..\n<br>\n<br>http://matz.egloos.com/1102066\n<br>여기서찾았습니다.\n<br>\n<br>kenu님답변감사합니다.";
		String output = "이렇게 하면 되네요..\n<br>\n<br><a href=\"http://matz.egloos.com/1102066\" target=\"_blank\">http://matz.egloos.com/1102066</a>\n<br>여기서찾았습니다.\n<br>\n<br>kenu님답변감사합니다.";
		assertEquals(output, HttpLinker.getLinkedSource(source));
	}
	public void testSkipLegacyLinkTag() {
		String source = "이렇게 하면 되네요..\n<br>\n<br><a href=\"http://matz.egloos.com/1102066\" target=\"_blank\">http://matz.egloos.com/1102066</a>\n<br>여기서찾았습니다.\n<br>\n<br>kenu님답변감사합니다.";
		String output = "이렇게 하면 되네요..\n<br>\n<br><a href=\"http://matz.egloos.com/1102066\" target=\"_blank\">http://matz.egloos.com/1102066</a>\n<br>여기서찾았습니다.\n<br>\n<br>kenu님답변감사합니다.";
		assertEquals(output, HttpLinker.getLinkedSource(source));
	}
	
	public void testGetLinkedSourceIframe() {
		String source = "<iframe title=\"YouTube video player\" width=\"480\" height=\"390\" src=\"http://www.youtube.com/embed/uPT4bHy0P3Y\" frameborder=\"0\" allowfullscreen></iframe>";
		String output = "<iframe title=\"YouTube video player\" width=\"480\" height=\"390\" src=\"http://www.youtube.com/embed/uPT4bHy0P3Y\" frameborder=\"0\" allowfullscreen></iframe>";
		assertEquals(output, HttpLinker.getLinkedSource(source));
	}
	
	public void testGetLinkedSourceEmbed() {
		String source = "<embed src=\"http://www.jjanglive.com/flash/webClient.swf?widgetseq=90120&movieseq=754497&defaultMute=0\" quality=\"high\" width=\"480\" height=\"360\" name=\"widgetFlash\" align=\"middle\" play=\"false\" loop=\"false\" allowFullScreen=\"true\" allowScriptAccess=\"always\" scale=\"noscale\" type=\"application/x-shockwave-flash\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\"></embed><p>";
		String output = "<embed src=\"http://www.jjanglive.com/flash/webClient.swf?widgetseq=90120&movieseq=754497&defaultMute=0\" quality=\"high\" width=\"480\" height=\"360\" name=\"widgetFlash\" align=\"middle\" play=\"false\" loop=\"false\" allowFullScreen=\"true\" allowScriptAccess=\"always\" scale=\"noscale\" type=\"application/x-shockwave-flash\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\"></embed><p>";
		assertEquals(output, HttpLinker.getLinkedSource(source));
	}
	
	public void testGetLinkedSourceEmbed2() {
		String source = "<P><EMBED height=360 name=widgetFlash type=application/x-shockwave-flash align=middle pluginspage=http://www.macromedia.com/go/getflashplayer width=480 src=http://www.jjanglive.com/flash/webClient.swf?widgetseq=14250&amp;movieseq=757760&amp;defaultMute=0 AllowScriptAccess=\"never\" autostart=\"false\" wmode=\"transparent\" scale=\"noscale\" allowScriptAccess=\"always\" allowFullScreen=\"true\" loop=\"false\" play=\"false\" quality=\"high\"></EMBED><BR><BR>부러우면 지는겁니다ㅠㅠ<BR></P>";
		String output = "<P><EMBED height=360 name=widgetFlash type=application/x-shockwave-flash align=middle pluginspage=http://www.macromedia.com/go/getflashplayer width=480 src=http://www.jjanglive.com/flash/webClient.swf?widgetseq=14250&amp;movieseq=757760&amp;defaultMute=0 AllowScriptAccess=\"never\" autostart=\"false\" wmode=\"transparent\" scale=\"noscale\" allowScriptAccess=\"always\" allowFullScreen=\"true\" loop=\"false\" play=\"false\" quality=\"high\"></EMBED><BR><BR>부러우면 지는겁니다ㅠㅠ<BR></P>";
		assertEquals(output, HttpLinker.getLinkedSource(source));
	}
	
	public void testGetLinkedSourceDup() {
		String source = "<P>출처 :&nbsp;<A href=\"http://sdnkorea.com/blog/584\">http://sdnkorea.com/blog/584</A></P>\n<P>&nbsp;</P>";
		String output = "<P>출처 :&nbsp;<A href=\"http://sdnkorea.com/blog/584\">http://sdnkorea.com/blog/584</A></P>\n<P>&nbsp;</P>";
		assertEquals(output, HttpLinker.getLinkedSource(source));
	}
	

}
