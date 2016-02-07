/*
 *      Copyright (c) 2004-2015 Stuart Boston
 *
 *      This file is part of the API Common project.
 *
 *      API Common is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation;private either version 3 of the License;private or
 *      any later version.
 *
 *      API Common is distributed in the hope that it will be useful;private
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *
 *      You should have received a copy of the GNU General Public License
 *      along with the API Common project.  If not;private see <http://www.gnu.org/licenses/>.
 *
 */
package common.http;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WebBrowserUserAgentSelector implements IUserAgentSelector {

    private static final Random RANDOM = new Random();
    private static final List<String> USER_AGENTS = new ArrayList<>();

    static {
        // INTERNET EXPLORER
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 10.6; Windows NT 6.1; Trident/5.0; InfoPath.2; SLCC1; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; .NET CLR 2.0.50727) 3gpp-gba UNTRUSTED/1.0");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; Trident/6.0)");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; Trident/5.0)");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; Trident/4.0; InfoPath.2; SV1; .NET CLR 2.0.50727; WOW64)");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 10.0; Macintosh; Intel Mac OS X 10_7_3; Trident/6.0)");
        USER_AGENTS.add("Mozilla/4.0 (compatible; MSIE 10.0; Windows NT 6.1; Trident/5.0)");
        USER_AGENTS.add("Mozilla/1.22 (compatible; MSIE 10.0; Windows 3.1)");
        USER_AGENTS.add("Mozilla/5.0 (Windows; U; MSIE 9.0; WIndows NT 9.0; en-US))");
        USER_AGENTS.add("Mozilla/5.0 (Windows; U; MSIE 9.0; Windows NT 9.0; en-US)");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 7.1; Trident/5.0)");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; Media Center PC 6.0; InfoPath.3; MS-RTC LM 8; Zune 4.7)");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; Media Center PC 6.0; InfoPath.3; MS-RTC LM 8; Zune 4.7");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; Zune 4.0; InfoPath.3; MS-RTC LM 8; .NET4.0C; .NET4.0E)");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; chromeframe/12.0.742.112)");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET CLR 2.0.50727; Media Center PC 6.0)");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET CLR 2.0.50727; Media Center PC 6.0)");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0; .NET CLR 2.0.50727; SLCC2; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; Zune 4.0; Tablet PC 2.0; InfoPath.3; .NET4.0C; .NET4.0E)");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; yie8)");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.2; .NET CLR 1.1.4322; .NET4.0C; Tablet PC 2.0)");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; FunWebProducts)");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; chromeframe/13.0.782.215)");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; chromeframe/11.0.696.57)");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0) chromeframe/10.0.648.205");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/4.0; GTB7.4; InfoPath.1; SV1; .NET CLR 2.8.52393; WOW64; en-US)");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.0; Trident/5.0; chromeframe/11.0.696.57)");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.0; Trident/4.0; GTB7.4; InfoPath.3; SV1; .NET CLR 3.1.76908; WOW64; en-US)");
        USER_AGENTS.add("Mozilla/5.0 (Windows; U; MSIE 9.0; WIndows NT 9.0; en-US))");
        USER_AGENTS.add("Mozilla/5.0 (Windows; U; MSIE 9.0; Windows NT 9.0; en-US)");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 7.1; Trident/5.0)");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; Media Center PC 6.0; InfoPath.3; MS-RTC LM 8; Zune 4.7)");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; Media Center PC 6.0; InfoPath.3; MS-RTC LM 8; Zune 4.7");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; Zune 4.0; InfoPath.3; MS-RTC LM 8; .NET4.0C; .NET4.0E)");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; chromeframe/12.0.742.112)");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET CLR 2.0.50727; Media Center PC 6.0)");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET CLR 2.0.50727; Media Center PC 6.0)");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0; .NET CLR 2.0.50727; SLCC2; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; Zune 4.0; Tablet PC 2.0; InfoPath.3; .NET4.0C; .NET4.0E)");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; yie8)");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.2; .NET CLR 1.1.4322; .NET4.0C; Tablet PC 2.0)");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; FunWebProducts)");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; chromeframe/13.0.782.215)");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; chromeframe/11.0.696.57)");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0) chromeframe/10.0.648.205");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/4.0; GTB7.4; InfoPath.1; SV1; .NET CLR 2.8.52393; WOW64; en-US)");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.0; Trident/5.0; chromeframe/11.0.696.57)");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.0; Trident/4.0; GTB7.4; InfoPath.3; SV1; .ET CLR 3.1.76908; WOW64; en-US)");
        USER_AGENTS.add("Mozilla/5.0 ( ; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");
        USER_AGENTS.add("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/4.0; GTB7.4; InfoPath.2; SV1; .NET CLR 4.4.58799; WOW64; en-US)");
        USER_AGENTS.add("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/4.0; FDM; MSIECrawler; Media Center PC 5.0)");
        USER_AGENTS.add("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.0; Trident/4.0; GTB7.4; InfoPath.3; SV1; .NET CLR 3.4.53360; WOW64; en-US)");
        USER_AGENTS.add("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 5.1; Trident/5.0)");
        USER_AGENTS.add("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 5.1; Trident/4.0; .NET CLR 2.0.50727; .NET CLR 1.1.4322; .NET CLR 3.0.04506.648; .NET CLR 3.5.21022; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; OfficeLiveConnector.1.4; OfficeLivePatch.1.3; .NET4.0C; .NE");
        USER_AGENTS.add("Mozilla/4.0 (compatible; MSIE 9.0; Windows 98; .NET CLR 3.0.04506.30)");
        USER_AGENTS.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 7.1; Trident/5.0; .NET CLR 2.0.50727; SLCC2; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.3; .NET4.0C)");
        USER_AGENTS.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E; AskTB5.5)");
        USER_AGENTS.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; InfoPath.2; .NET4.0C; .NET4.0E)");
        USER_AGENTS.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; Win64; x64; Trident/5.0; .NET4.0C; .NET4.0E; InfoPath.3)");
        USER_AGENTS.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; Win64; x64; Trident/5.0; .NET CLR 2.0.50727; SLCC2; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.3; .NET4.0C)");
        USER_AGENTS.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET4.0C)");
        USER_AGENTS.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; FDM; .NET CLR 1.1.4322; .NET4.0C; .NET4.0E; Tablet PC 2.0)");
        USER_AGENTS.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; Tablet PC 2.0; InfoPath.3; .NET4.0E)");
        USER_AGENTS.add("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; Trident/5.0; SLCC1; .NET CLR 2.0.50727; Media Center PC 5.0; .NET CLR 3.5.30729; .NET CLR 3.0.30729; FDM; .NET4.0C; .NET4.0E; chromeframe/11.0.696.57)");
        USER_AGENTS.add("Mozilla/4.0 (compatible; U; MSIE 9.0; WIndows NT 9.0; en-US)");
        USER_AGENTS.add("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; FunWebProducts)");
        
        // FIREFOX
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.6; rv:25.0) Gecko/20100101 Firefox/25.0");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.0; WOW64; rv:24.0) Gecko/20100101 Firefox/24.0");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:24.0) Gecko/20100101 Firefox/24.0");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.2; rv:22.0) Gecko/20130405 Firefox/23.0");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20130406 Firefox/23.0");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:23.0) Gecko/20131011 Firefox/23.0");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.2; rv:22.0) Gecko/20130405 Firefox/22.0");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:22.0) Gecko/20130328 Firefox/22.0");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.1; rv:22.0) Gecko/20130405 Firefox/22.0");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.2; Win64; x64; rv:16.0.1) Gecko/20121011 Firefox/21.0.1");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:16.0.1) Gecko/20121011 Firefox/21.0.1");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.2; Win64; x64; rv:21.0.0) Gecko/20121011 Firefox/21.0.0");
        USER_AGENTS.add("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:21.0) Gecko/20130331 Firefox/21.0");
        USER_AGENTS.add("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:21.0) Gecko/20100101 Firefox/21.0");
        USER_AGENTS.add("Mozilla/5.0 (X11; Linux i686; rv:21.0) Gecko/20100101 Firefox/21.0");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.2; rv:21.0) Gecko/20130326 Firefox/21.0");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:21.0) Gecko/20130401 Firefox/21.0");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:21.0) Gecko/20130331 Firefox/21.0");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:21.0) Gecko/20130330 Firefox/21.0");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:21.0) Gecko/20100101 Firefox/21.0");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.1; rv:21.0) Gecko/20130401 Firefox/21.0");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.1; rv:21.0) Gecko/20130328 Firefox/21.0");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.1; rv:21.0) Gecko/20100101 Firefox/21.0");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 5.1; rv:21.0) Gecko/20130401 Firefox/21.0");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 5.1; rv:21.0) Gecko/20130331 Firefox/21.0");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 5.1; rv:21.0) Gecko/20100101 Firefox/21.0");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 5.0; rv:21.0) Gecko/20100101 Firefox/21.0");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:21.0) Gecko/20100101 Firefox/21.0");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.2; Win64; x64;) Gecko/20100101 Firefox/20.0");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.1; rv:6.0) Gecko/20100101 Firefox/19.0");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.1; rv:14.0) Gecko/20100101 Firefox/18.0.1");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:18.0) Gecko/20100101 Firefox/18.0");
        USER_AGENTS.add("Mozilla/5.0 (X11; Ubuntu; Linux armv7l; rv:17.0) Gecko/20100101 Firefox/17.0");
        USER_AGENTS.add("Mozilla/6.0 (Windows NT 6.2; WOW64; rv:16.0.1) Gecko/20121011 Firefox/16.0.1");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.2; WOW64; rv:16.0.1) Gecko/20121011 Firefox/16.0.1");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.2; Win64; x64; rv:16.0.1) Gecko/20121011 Firefox/16.0.1");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.1; rv:15.0) Gecko/20120716 Firefox/15.0a2");
        USER_AGENTS.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.1.16) Gecko/20120427 Firefox/15.0a1");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:15.0) Gecko/20120427 Firefox/15.0a1");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.2; WOW64; rv:15.0) Gecko/20120910144328 Firefox/15.0.2");
        USER_AGENTS.add("Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:15.0) Gecko/20100101 Firefox/15.0.1");
        USER_AGENTS.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; rv:15.0) Gecko/20121011 Firefox/15.0.1");
                        
        // CHROME
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.17 Safari/537.36");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.62 Safari/537.36");
        USER_AGENTS.add("Mozilla/5.0 (X11; CrOS i686 4319.74.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.57 Safari/537.36");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.2 Safari/537.36");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1468.0 Safari/537.36");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1467.0 Safari/537.36");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1464.0 Safari/537.36");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.93 Safari/537.36");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.93 Safari/537.36");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.93 Safari/537.36");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.93 Safari/537.36");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.93 Safari/537.36");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.93 Safari/537.36");
        USER_AGENTS.add("Mozilla/5.0 (X11; CrOS i686 3912.101.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.116 Safari/537.36");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.17 (KHTML, like Gecko) Chrome/24.0.1312.60 Safari/537.17");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_2) AppleWebKit/537.17 (KHTML, like Gecko) Chrome/24.0.1309.0 Safari/537.17");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.15 (KHTML, like Gecko) Chrome/24.0.1295.0 Safari/537.15");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.14 (KHTML, like Gecko) Chrome/24.0.1292.0 Safari/537.14");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.13 (KHTML, like Gecko) Chrome/24.0.1290.1 Safari/537.13");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.2) AppleWebKit/537.13 (KHTML, like Gecko) Chrome/24.0.1290.1 Safari/537.13");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_2) AppleWebKit/537.13 (KHTML, like Gecko) Chrome/24.0.1290.1 Safari/537.13");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_4) AppleWebKit/537.13 (KHTML, like Gecko) Chrome/24.0.1290.1 Safari/537.13");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.13 (KHTML, like Gecko) Chrome/24.0.1284.0 Safari/537.13");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_2) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.6 Safari/537.11");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.2) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.26 Safari/537.11");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.0) yi; AppleWebKit/345667.12221 (KHTML, like Gecko) Chrome/23.0.1271.26 Safari/453667.1221");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.17 Safari/537.11");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.2) AppleWebKit/537.4 (KHTML, like Gecko) Chrome/22.0.1229.94 Safari/537.4");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_0) AppleWebKit/537.4 (KHTML, like Gecko) Chrome/22.0.1229.79 Safari/537.4");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.2 (KHTML, like Gecko) Chrome/22.0.1216.0 Safari/537.2");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/22.0.1207.1 Safari/537.1");
        USER_AGENTS.add("Mozilla/5.0 (X11; CrOS i686 2268.111.0) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.57 Safari/536.11");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.6 (KHTML, like Gecko) Chrome/20.0.1092.0 Safari/536.6");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.6 (KHTML, like Gecko) Chrome/20.0.1090.0 Safari/536.6");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/19.77.34.5 Safari/537.1");
        USER_AGENTS.add("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.9 Safari/536.5");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.0) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.36 Safari/536.5");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1063.0 Safari/536.3");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 5.1) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1063.0 Safari/536.3");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_0) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1063.0 Safari/536.3");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1062.0 Safari/536.3");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1062.0 Safari/536.3");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1061.1 Safari/536.3");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1061.1 Safari/536.3");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1061.1 Safari/536.3");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1061.0 Safari/536.3");
        USER_AGENTS.add("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.24 (KHTML, like Gecko) Chrome/19.0.1055.1 Safari/535.24");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/535.24 (KHTML, like Gecko) Chrome/19.0.1055.1 Safari/535.24");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/535.24 (KHTML, like Gecko) Chrome/19.0.1055.1 Safari/535.24");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_3) AppleWebKit/535.22 (KHTML, like Gecko) Chrome/19.0.1047.0 Safari/535.22");
        USER_AGENTS.add("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21");
        USER_AGENTS.add("Mozilla/5.0 (X11; Linux i686) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1041.0 Safari/535.21");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_3) AppleWebKit/535.20 (KHTML, like Gecko) Chrome/19.0.1036.7 Safari/535.20");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/18.6.872.0 Safari/535.2 UNTRUSTED/1.0 3gpp-gba UNTRUSTED/1.0");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; AMD Mac OS X 10_8_2) AppleWebKit/535.22 (KHTML, like Gecko) Chrome/18.6.872");
        USER_AGENTS.add("Mozilla/5.0 (X11; CrOS i686 1660.57.0) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.46 Safari/535.19");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.0; WOW64) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.45 Safari/535.19");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.45 Safari/535.19");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.45 Safari/535.19");

        // SAFARI
        USER_AGENTS.add("Mozilla/5.0 (iPad; CPU OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A5355d Safari/8536.25");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/537.13+ (KHTML, like Gecko) Version/5.1.7 Safari/534.57.2");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_3) AppleWebKit/534.55.3 (KHTML, like Gecko) Version/5.1.3 Safari/534.53.10");
        USER_AGENTS.add("Mozilla/5.0 (iPad; CPU OS 5_1 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko ) Version/5.1 Mobile/9B176 Safari/7534.48.3");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_8; de-at) AppleWebKit/533.21.1 (KHTML, like Gecko) Version/5.0.5 Safari/533.21.1");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_7; da-dk) AppleWebKit/533.21.1 (KHTML, like Gecko) Version/5.0.5 Safari/533.21.1");
        USER_AGENTS.add("Mozilla/5.0 (Windows; U; Windows NT 6.1; tr-TR) AppleWebKit/533.20.25 (KHTML, like Gecko) Version/5.0.4 Safari/533.20.27");
        USER_AGENTS.add("Mozilla/5.0 (Windows; U; Windows NT 6.1; ko-KR) AppleWebKit/533.20.25 (KHTML, like Gecko) Version/5.0.4 Safari/533.20.27");
        USER_AGENTS.add("Mozilla/5.0 (Windows; U; Windows NT 6.1; fr-FR) AppleWebKit/533.20.25 (KHTML, like Gecko) Version/5.0.4 Safari/533.20.27");
        USER_AGENTS.add("Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/533.20.25 (KHTML, like Gecko) Version/5.0.4 Safari/533.20.27");
        USER_AGENTS.add("Mozilla/5.0 (Windows; U; Windows NT 6.1; cs-CZ) AppleWebKit/533.20.25 (KHTML, like Gecko) Version/5.0.4 Safari/533.20.27");
        USER_AGENTS.add("Mozilla/5.0 (Windows; U; Windows NT 6.0; ja-JP) AppleWebKit/533.20.25 (KHTML, like Gecko) Version/5.0.4 Safari/533.20.27");
        USER_AGENTS.add("Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US) AppleWebKit/533.20.25 (KHTML, like Gecko) Version/5.0.4 Safari/533.20.27");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; U; PPC Mac OS X 10_5_8; zh-cn) AppleWebKit/533.20.25 (KHTML, like Gecko) Version/5.0.4 Safari/533.20.27");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; U; PPC Mac OS X 10_5_8; ja-jp) AppleWebKit/533.20.25 (KHTML, like Gecko) Version/5.0.4 Safari/533.20.27");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_7; ja-jp) AppleWebKit/533.20.25 (KHTML, like Gecko) Version/5.0.4 Safari/533.20.27");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_6; zh-cn) AppleWebKit/533.20.25 (KHTML, like Gecko) Version/5.0.4 Safari/533.20.27");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_6; sv-se) AppleWebKit/533.20.25 (KHTML, like Gecko) Version/5.0.4 Safari/533.20.27");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_6; ko-kr) AppleWebKit/533.20.25 (KHTML, like Gecko) Version/5.0.4 Safari/533.20.27");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_6; ja-jp) AppleWebKit/533.20.25 (KHTML, like Gecko) Version/5.0.4 Safari/533.20.27");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_6; it-it) AppleWebKit/533.20.25 (KHTML, like Gecko) Version/5.0.4 Safari/533.20.27");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_6; fr-fr) AppleWebKit/533.20.25 (KHTML, like Gecko) Version/5.0.4 Safari/533.20.27");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_6; es-es) AppleWebKit/533.20.25 (KHTML, like Gecko) Version/5.0.4 Safari/533.20.27");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_6; en-us) AppleWebKit/533.20.25 (KHTML, like Gecko) Version/5.0.4 Safari/533.20.27");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_6; en-gb) AppleWebKit/533.20.25 (KHTML, like Gecko) Version/5.0.4 Safari/533.20.27");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_6; de-de) AppleWebKit/533.20.25 (KHTML, like Gecko) Version/5.0.4 Safari/533.20.27");
        USER_AGENTS.add("Mozilla/5.0 (Windows; U; Windows NT 6.1; sv-SE) AppleWebKit/533.19.4 (KHTML, like Gecko) Version/5.0.3 Safari/533.19.4");
        USER_AGENTS.add("Mozilla/5.0 (Windows; U; Windows NT 6.1; ja-JP) AppleWebKit/533.20.25 (KHTML, like Gecko) Version/5.0.3 Safari/533.19.4");
        USER_AGENTS.add("Mozilla/5.0 (Windows; U; Windows NT 6.1; de-DE) AppleWebKit/533.20.25 (KHTML, like Gecko) Version/5.0.3 Safari/533.19.4");
        USER_AGENTS.add("Mozilla/5.0 (Windows; U; Windows NT 6.0; hu-HU) AppleWebKit/533.19.4 (KHTML, like Gecko) Version/5.0.3 Safari/533.19.4");
        USER_AGENTS.add("Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US) AppleWebKit/533.20.25 (KHTML, like Gecko) Version/5.0.3 Safari/533.19.4");
        USER_AGENTS.add("Mozilla/5.0 (Windows; U; Windows NT 6.0; de-DE) AppleWebKit/533.20.25 (KHTML, like Gecko) Version/5.0.3 Safari/533.19.4");
        USER_AGENTS.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; ru-RU) AppleWebKit/533.19.4 (KHTML, like Gecko) Version/5.0.3 Safari/533.19.4");
        USER_AGENTS.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; ja-JP) AppleWebKit/533.20.25 (KHTML, like Gecko) Version/5.0.3 Safari/533.19.4");
        USER_AGENTS.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; it-IT) AppleWebKit/533.20.25 (KHTML, like Gecko) Version/5.0.3 Safari/533.19.4");
        USER_AGENTS.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/533.20.25 (KHTML, like Gecko) Version/5.0.3 Safari/533.19.4");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_7; en-us) AppleWebKit/534.16+ (KHTML, like Gecko) Version/5.0.3 Safari/533.19.4");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_6; fr-ch) AppleWebKit/533.19.4 (KHTML, like Gecko) Version/5.0.3 Safari/533.19.4");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_5; de-de) AppleWebKit/534.15+ (KHTML, like Gecko) Version/5.0.3 Safari/533.19.4");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_5; ar) AppleWebKit/533.19.4 (KHTML, like Gecko) Version/5.0.3 Safari/533.19.4");
        USER_AGENTS.add("Mozilla/5.0 (Android 2.2; Windows; U; Windows NT 6.1; en-US) AppleWebKit/533.19.4 (KHTML, like Gecko) Version/5.0.3 Safari/533.19.4");
        USER_AGENTS.add("Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-HK) AppleWebKit/533.18.1 (KHTML, like Gecko) Version/5.0.2 Safari/533.18.5");
        USER_AGENTS.add("Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/533.19.4 (KHTML, like Gecko) Version/5.0.2 Safari/533.18.5");
        USER_AGENTS.add("Mozilla/5.0 (Windows; U; Windows NT 6.0; tr-TR) AppleWebKit/533.18.1 (KHTML, like Gecko) Version/5.0.2 Safari/533.18.5");
        USER_AGENTS.add("Mozilla/5.0 (Windows; U; Windows NT 6.0; nb-NO) AppleWebKit/533.18.1 (KHTML, like Gecko) Version/5.0.2 Safari/533.18.5");
        USER_AGENTS.add("Mozilla/5.0 (Windows; U; Windows NT 6.0; fr-FR) AppleWebKit/533.18.1 (KHTML, like Gecko) Version/5.0.2 Safari/533.18.5");
        USER_AGENTS.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-TW) AppleWebKit/533.19.4 (KHTML, like Gecko) Version/5.0.2 Safari/533.18.5");
        USER_AGENTS.add("Mozilla/5.0 (Windows; U; Windows NT 5.1; ru-RU) AppleWebKit/533.18.1 (KHTML, like Gecko) Version/5.0.2 Safari/533.18.5");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_8; zh-cn) AppleWebKit/533.18.1 (KHTML, like Gecko) Version/5.0.2 Safari/533.18.5");
        USER_AGENTS.add("Mozilla/5.0 (iPod; U; CPU iPhone OS 4_3_3 like Mac OS X; ja-jp) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5");
        USER_AGENTS.add("Mozilla/5.0 (iPod; U; CPU iPhone OS 4_3_1 like Mac OS X; zh-cn) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8G4 Safari/6533.18.5");
        USER_AGENTS.add("Mozilla/5.0 (iPod; U; CPU iPhone OS 4_2_1 like Mac OS X; he-il) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8C148 Safari/6533.18.5");
        USER_AGENTS.add("Mozilla/5.0 (iPhone; U; ru; CPU iPhone OS 4_2_1 like Mac OS X; ru) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8C148a Safari/6533.18.5");
        USER_AGENTS.add("Mozilla/5.0 (iPhone; U; ru; CPU iPhone OS 4_2_1 like Mac OS X; fr) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8C148a Safari/6533.18.5");
        USER_AGENTS.add("Mozilla/5.0 (iPhone; U; fr; CPU iPhone OS 4_2_1 like Mac OS X; fr) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8C148a Safari/6533.18.5");
        USER_AGENTS.add("Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_3_1 like Mac OS X; zh-tw) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8G4 Safari/6533.18.5");
        USER_AGENTS.add("Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_3 like Mac OS X; pl-pl) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8F190 Safari/6533.18.5");
        USER_AGENTS.add("Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_3 like Mac OS X; fr-fr) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8F190 Safari/6533.18.5");
        USER_AGENTS.add("Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_3 like Mac OS X; en-gb) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8F190 Safari/6533.18.5");
        USER_AGENTS.add("Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_2_1 like Mac OS X; ru-ru) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8C148 Safari/6533.18.5");
        USER_AGENTS.add("Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_2_1 like Mac OS X; nb-no) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8C148a Safari/6533.18.5");
        USER_AGENTS.add("Mozilla/5.0 (Windows; U; Windows NT 5.2; en-US) AppleWebKit/533.17.8 (KHTML, like Gecko) Version/5.0.1 Safari/533.17.8");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_4; th-th) AppleWebKit/533.17.8 (KHTML, like Gecko) Version/5.0.1 Safari/533.17.8");
        USER_AGENTS.add("Mozilla/5.0 (X11; U; Linux x86_64; en-us) AppleWebKit/531.2+ (KHTML, like Gecko) Version/5.0 Safari/531.2+");
        USER_AGENTS.add("Mozilla/5.0 (X11; U; Linux x86_64; en-ca) AppleWebKit/531.2+ (KHTML, like Gecko) Version/5.0 Safari/531.2+");
        USER_AGENTS.add("Mozilla/5.0 (Windows; U; Windows NT 6.1; ja-JP) AppleWebKit/533.16 (KHTML, like Gecko) Version/5.0 Safari/533.16");
        USER_AGENTS.add("Mozilla/5.0 (Windows; U; Windows NT 6.1; es-ES) AppleWebKit/533.18.1 (KHTML, like Gecko) Version/5.0 Safari/533.16");
        USER_AGENTS.add("Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/533.18.1 (KHTML, like Gecko) Version/5.0 Safari/533.16");
        USER_AGENTS.add("Mozilla/5.0 (Windows; U; Windows NT 6.0; ja-JP) AppleWebKit/533.16 (KHTML, like Gecko) Version/5.0 Safari/533.16");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; U; PPC Mac OS X 10_5_8; ja-jp) AppleWebKit/533.16 (KHTML, like Gecko) Version/5.0 Safari/533.16");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; U; PPC Mac OS X 10_4_11; fr) AppleWebKit/533.16 (KHTML, like Gecko) Version/5.0 Safari/533.16");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_3; zh-cn) AppleWebKit/533.16 (KHTML, like Gecko) Version/5.0 Safari/533.16");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_3; ru-ru) AppleWebKit/533.16 (KHTML, like Gecko) Version/5.0 Safari/533.16");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_3; ko-kr) AppleWebKit/533.16 (KHTML, like Gecko) Version/5.0 Safari/533.16");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_3; it-it) AppleWebKit/533.16 (KHTML, like Gecko) Version/5.0 Safari/533.16");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_3; HTC-P715a; en-ca) AppleWebKit/533.16 (KHTML, like Gecko) Version/5.0 Safari/533.16");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_3; en-us) AppleWebKit/534.1+ (KHTML, like Gecko) Version/5.0 Safari/533.16");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_3; en-au) AppleWebKit/533.16 (KHTML, like Gecko) Version/5.0 Safari/533.16");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_3; el-gr) AppleWebKit/533.16 (KHTML, like Gecko) Version/5.0 Safari/533.16");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_3; ca-es) AppleWebKit/533.16 (KHTML, like Gecko) Version/5.0 Safari/533.16");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_8; zh-tw) AppleWebKit/533.16 (KHTML, like Gecko) Version/5.0 Safari/533.16");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_8; ja-jp) AppleWebKit/533.16 (KHTML, like Gecko) Version/5.0 Safari/533.16");
        USER_AGENTS.add("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_8; it-it) AppleWebKit/533.16 (KHTML, like Gecko) Version/5.0 Safari/533.16");        
        
        // OPERA
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.0) Presto/2.12.388 Version/12.14");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.0; rv:2.0) Gecko/20100101 Firefox/4.0 Opera 12.14");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.0) Opera 12.14");
        USER_AGENTS.add("Opera/12.80 (Windows NT 5.1; U; en) Presto/2.10.289 Version/12.02");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.1; U; es-ES) Presto/2.9.181 Version/12.00");
        USER_AGENTS.add("Opera/9.80 (Windows NT 5.1; U; zh-sg) Presto/2.9.181 Version/12.00");
        USER_AGENTS.add("Opera/12.0(Windows NT 5.2;U;en)Presto/22.9.168 Version/12.00");
        USER_AGENTS.add("Opera/12.0(Windows NT 5.1;U;en)Presto/22.9.168 Version/12.00");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 5.1) Gecko/20100101 Firefox/14.0 Opera/12.0");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.1; WOW64; U; pt) Presto/2.10.229 Version/11.62");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.0; U; pl) Presto/2.10.229 Version/11.62");
        USER_AGENTS.add("Opera/9.80 (Macintosh; Intel Mac OS X 10.6.8; U; fr) Presto/2.9.168 Version/11.52");
        USER_AGENTS.add("Opera/9.80 (Macintosh; Intel Mac OS X 10.6.8; U; de) Presto/2.9.168 Version/11.52");
        USER_AGENTS.add("Opera/9.80 (Windows NT 5.1; U; en) Presto/2.9.168 Version/11.51");
        USER_AGENTS.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; de) Opera 11.51");
        USER_AGENTS.add("Opera/9.80 (X11; Linux x86_64; U; fr) Presto/2.9.168 Version/11.50");
        USER_AGENTS.add("Opera/9.80 (X11; Linux i686; U; hu) Presto/2.9.168 Version/11.50");
        USER_AGENTS.add("Opera/9.80 (X11; Linux i686; U; ru) Presto/2.8.131 Version/11.11");
        USER_AGENTS.add("Opera/9.80 (X11; Linux i686; U; es-ES) Presto/2.8.131 Version/11.11");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 5.1; U; en; rv:1.8.1) Gecko/20061208 Firefox/5.0 Opera 11.11");
        USER_AGENTS.add("Opera/9.80 (X11; Linux x86_64; U; bg) Presto/2.8.131 Version/11.10");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.0; U; en) Presto/2.8.99 Version/11.10");
        USER_AGENTS.add("Opera/9.80 (Windows NT 5.1; U; zh-tw) Presto/2.8.131 Version/11.10");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.1; Opera Tablet/15165; U; en) Presto/2.8.149 Version/11.1");
        USER_AGENTS.add("Opera/9.80 (X11; Linux x86_64; U; Ubuntu/10.10 (maverick); pl) Presto/2.7.62 Version/11.01");
        USER_AGENTS.add("Opera/9.80 (X11; Linux i686; U; ja) Presto/2.7.62 Version/11.01");
        USER_AGENTS.add("Opera/9.80 (X11; Linux i686; U; fr) Presto/2.7.62 Version/11.01");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.1; U; zh-tw) Presto/2.7.62 Version/11.01");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.1; U; zh-cn) Presto/2.7.62 Version/11.01");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.1; U; sv) Presto/2.7.62 Version/11.01");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.1; U; en-US) Presto/2.7.62 Version/11.01");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.1; U; cs) Presto/2.7.62 Version/11.01");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.0; U; pl) Presto/2.7.62 Version/11.01");
        USER_AGENTS.add("Opera/9.80 (Windows NT 5.2; U; ru) Presto/2.7.62 Version/11.01");
        USER_AGENTS.add("Opera/9.80 (Windows NT 5.1; U;) Presto/2.7.62 Version/11.01");
        USER_AGENTS.add("Opera/9.80 (Windows NT 5.1; U; cs) Presto/2.7.62 Version/11.01");
        USER_AGENTS.add("Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US; rv:1.9.2.13) Gecko/20101213 Opera/9.80 (Windows NT 6.1; U; zh-tw) Presto/2.7.62 Version/11.01");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.1; U; nl; rv:1.9.1.6) Gecko/20091201 Firefox/3.5.6 Opera 11.01");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.1; U; de; rv:1.9.1.6) Gecko/20091201 Firefox/3.5.6 Opera 11.01");
        USER_AGENTS.add("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; de) Opera 11.01");
        USER_AGENTS.add("Opera/9.80 (X11; Linux x86_64; U; pl) Presto/2.7.62 Version/11.00");
        USER_AGENTS.add("Opera/9.80 (X11; Linux i686; U; it) Presto/2.7.62 Version/11.00");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.1; U; zh-cn) Presto/2.6.37 Version/11.00");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.1; U; pl) Presto/2.7.62 Version/11.00");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.1; U; ko) Presto/2.7.62 Version/11.00");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.1; U; fi) Presto/2.7.62 Version/11.00");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.1; U; en-GB) Presto/2.7.62 Version/11.00");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.1 x64; U; en) Presto/2.7.62 Version/11.00");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.0; U; en) Presto/2.7.39 Version/11.00");
        USER_AGENTS.add("Opera/9.80 (Windows NT 5.1; U; ru) Presto/2.7.39 Version/11.00");
        USER_AGENTS.add("Opera/9.80 (Windows NT 5.1; U; MRA 5.5 (build 02842); ru) Presto/2.7.62 Version/11.00");
        USER_AGENTS.add("Opera/9.80 (Windows NT 5.1; U; it) Presto/2.7.62 Version/11.00");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.0; U; ja; rv:1.9.1.6) Gecko/20091201 Firefox/3.5.6 Opera 11.00");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 5.1; U; pl; rv:1.9.1.6) Gecko/20091201 Firefox/3.5.6 Opera 11.00");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 5.1; U; de; rv:1.9.1.6) Gecko/20091201 Firefox/3.5.6 Opera 11.00");
        USER_AGENTS.add("Mozilla/4.0 (compatible; MSIE 8.0; X11; Linux x86_64; pl) Opera 11.00");
        USER_AGENTS.add("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; fr) Opera 11.00");
        USER_AGENTS.add("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; ja) Opera 11.00");
        USER_AGENTS.add("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; en) Opera 11.00");
        USER_AGENTS.add("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; pl) Opera 11.00");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.1; U; pl) Presto/2.6.31 Version/10.70");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 5.2; U; ru; rv:1.9.1.6) Gecko/20091201 Firefox/3.5.6 Opera 10.70");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 5.1; U; zh-cn; rv:1.9.1.6) Gecko/20091201 Firefox/3.5.6 Opera 10.70");
        USER_AGENTS.add("Opera/9.80 (Windows NT 5.2; U; zh-cn) Presto/2.6.30 Version/10.63");
        USER_AGENTS.add("Opera/9.80 (Windows NT 5.2; U; en) Presto/2.6.30 Version/10.63");
        USER_AGENTS.add("Opera/9.80 (Windows NT 5.1; U; MRA 5.6 (build 03278); ru) Presto/2.6.30 Version/10.63");
        USER_AGENTS.add("Opera/9.80 (Windows NT 5.1; U; pl) Presto/2.6.30 Version/10.62");
        USER_AGENTS.add("Mozilla/5.0 (X11; Linux x86_64; U; de; rv:1.9.1.6) Gecko/20091201 Firefox/3.5.6 Opera 10.62");
        USER_AGENTS.add("Mozilla/4.0 (compatible; MSIE 8.0; X11; Linux x86_64; de) Opera 10.62");
        USER_AGENTS.add("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; en) Opera 10.62");
        USER_AGENTS.add("Opera/9.80 (X11; Linux i686; U; pl) Presto/2.6.30 Version/10.61");
        USER_AGENTS.add("Opera/9.80 (X11; Linux i686; U; es-ES) Presto/2.6.30 Version/10.61");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.1; U; zh-cn) Presto/2.6.30 Version/10.61");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.1; U; en) Presto/2.6.30 Version/10.61");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.0; U; it) Presto/2.6.30 Version/10.61");
        USER_AGENTS.add("Opera/9.80 (Windows NT 5.2; U; ru) Presto/2.6.30 Version/10.61");
        USER_AGENTS.add("Opera/9.80 (Windows 98; U; de) Presto/2.6.30 Version/10.61");
        USER_AGENTS.add("Opera/9.80 (Macintosh; Intel Mac OS X; U; nl) Presto/2.6.30 Version/10.61");
        USER_AGENTS.add("Opera/9.80 (X11; Linux i686; U; en) Presto/2.5.27 Version/10.60");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.0; U; nl) Presto/2.6.30 Version/10.60");
        USER_AGENTS.add("Opera/10.60 (Windows NT 5.1; U; zh-cn) Presto/2.6.30 Version/10.60");
        USER_AGENTS.add("Opera/10.60 (Windows NT 5.1; U; en-US) Presto/2.6.30 Version/10.60");
        USER_AGENTS.add("Opera/9.80 (X11; Linux i686; U; it) Presto/2.5.24 Version/10.54");
        USER_AGENTS.add("Opera/9.80 (X11; Linux i686; U; en-GB) Presto/2.5.24 Version/10.53");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 5.1; U; zh-cn; rv:1.9.1.6) Gecko/20091201 Firefox/3.5.6 Opera 10.53");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 5.1; U; Firefox/5.0; en; rv:1.9.1.6) Gecko/20091201 Firefox/3.5.6 Opera 10.53");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 5.1; U; Firefox/4.5; en; rv:1.9.1.6) Gecko/20091201 Firefox/3.5.6 Opera 10.53");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 5.1; U; Firefox/3.5; en; rv:1.9.1.6) Gecko/20091201 Firefox/3.5.6 Opera 10.53");
        USER_AGENTS.add("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; ko) Opera 10.53");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.1; U; fr) Presto/2.5.24 Version/10.52");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.1; U; en) Presto/2.5.22 Version/10.51");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.0; U; cs) Presto/2.5.22 Version/10.51");
        USER_AGENTS.add("Opera/9.80 (Windows NT 5.2; U; ru) Presto/2.5.22 Version/10.51");
        USER_AGENTS.add("Opera/9.80 (Linux i686; U; en) Presto/2.5.22 Version/10.51");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.1; U; en-GB; rv:1.9.1.6) Gecko/20091201 Firefox/3.5.6 Opera 10.51");
        USER_AGENTS.add("Mozilla/5.0 (Linux i686; U; en; rv:1.9.1.6) Gecko/20091201 Firefox/3.5.6 Opera 10.51");
        USER_AGENTS.add("Mozilla/4.0 (compatible; MSIE 8.0; Linux i686; en) Opera 10.51");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.1; U; zh-tw) Presto/2.5.22 Version/10.50");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.1; U; zh-cn) Presto/2.5.22 Version/10.50");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.1; U; sk) Presto/2.6.22 Version/10.50");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.1; U; ja) Presto/2.5.22 Version/10.50");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.0; U; zh-cn) Presto/2.5.22 Version/10.50");
        USER_AGENTS.add("Opera/9.80 (Windows NT 5.1; U; sk) Presto/2.5.22 Version/10.50");
        USER_AGENTS.add("Opera/9.80 (Windows NT 5.1; U; ru) Presto/2.5.22 Version/10.50");
        USER_AGENTS.add("Opera/10.50 (Windows NT 6.1; U; en-GB) Presto/2.2.2");
        USER_AGENTS.add("Opera/9.80 (S60; SymbOS; Opera Tablet/9174; U; en) Presto/2.7.81 Version/10.5");
        USER_AGENTS.add("Opera/9.80 (X11; U; Linux i686; en-US; rv:1.9.2.3) Presto/2.2.15 Version/10.10");
        USER_AGENTS.add("Opera/9.80 (X11; Linux x86_64; U; it) Presto/2.2.15 Version/10.10");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.1; U; de) Presto/2.2.15 Version/10.10");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.0; U; Gecko/20100115; pl) Presto/2.2.15 Version/10.10");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.0; U; en) Presto/2.2.15 Version/10.10");
        USER_AGENTS.add("Opera/9.80 (Windows NT 5.1; U; de) Presto/2.2.15 Version/10.10");
        USER_AGENTS.add("Opera/9.80 (Windows NT 5.1; U; cs) Presto/2.2.15 Version/10.10");
        USER_AGENTS.add("Mozilla/5.0 (Windows NT 6.0; U; tr; rv:1.8.1) Gecko/20061208 Firefox/2.0.0 Opera 10.10");
        USER_AGENTS.add("Mozilla/4.0 (compatible; MSIE 6.0; X11; Linux i686; de) Opera 10.10");
        USER_AGENTS.add("Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 6.0; tr) Opera 10.10");
        USER_AGENTS.add("Opera/9.80 (X11; Linux x86_64; U; en-GB) Presto/2.2.15 Version/10.01");
        USER_AGENTS.add("Opera/9.80 (X11; Linux x86_64; U; en) Presto/2.2.15 Version/10.00");
        USER_AGENTS.add("Opera/9.80 (X11; Linux x86_64; U; de) Presto/2.2.15 Version/10.00");
        USER_AGENTS.add("Opera/9.80 (X11; Linux i686; U; ru) Presto/2.2.15 Version/10.00");
        USER_AGENTS.add("Opera/9.80 (X11; Linux i686; U; pt-BR) Presto/2.2.15 Version/10.00");
        USER_AGENTS.add("Opera/9.80 (X11; Linux i686; U; pl) Presto/2.2.15 Version/10.00");
        USER_AGENTS.add("Opera/9.80 (X11; Linux i686; U; nb) Presto/2.2.15 Version/10.00");
        USER_AGENTS.add("Opera/9.80 (X11; Linux i686; U; en-GB) Presto/2.2.15 Version/10.00");
        USER_AGENTS.add("Opera/9.80 (X11; Linux i686; U; en) Presto/2.2.15 Version/10.00");
        USER_AGENTS.add("Opera/9.80 (X11; Linux i686; U; Debian; pl) Presto/2.2.15 Version/10.00");
        USER_AGENTS.add("Opera/9.80 (X11; Linux i686; U; de) Presto/2.2.15 Version/10.00");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.1; U; zh-cn) Presto/2.2.15 Version/10.00");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.1; U; fi) Presto/2.2.15 Version/10.00");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.1; U; en) Presto/2.2.15 Version/10.00");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.1; U; de) Presto/2.2.15 Version/10.00");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.1; U; cs) Presto/2.2.15 Version/10.00");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.0; U; en) Presto/2.2.15 Version/10.00");
        USER_AGENTS.add("Opera/9.80 (Windows NT 6.0; U; de) Presto/2.2.15 Version/10.00");
        USER_AGENTS.add("Opera/9.80 (Windows NT 5.2; U; en) Presto/2.2.15 Version/10.00");
        USER_AGENTS.add("Opera/9.80 (Windows NT 5.1; U; zh-cn) Presto/2.2.15 Version/10.00");
        USER_AGENTS.add("Opera/9.80 (Windows NT 5.1; U; ru) Presto/2.2.15 Version/10.00");     
    }

    /**
     * Get a random user agent to use for web requests
     *
     * @return a random user agent
     */
    @Override
    public String getUserAgent() {
        final int i = RANDOM.nextInt(USER_AGENTS.size());
        return USER_AGENTS.get(i);
    }
}
