import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

/**
 * @author barenx@gmail.com copy all right
 *
 */
public class HttpRequest {
	public static final String	HTTP_HEADER_ACCEPT				= "Accept";
	public static final String	HTTP_HEADER_ACCEPT_LANG			= "Accept-Language";
	public static final String	HTTP_HEADER_ACCEPT_ENCODING	= "Accept-Encoding";
	public static final String	HTTP_HEADER_UA						= "User-Agent";
	public static final String	HTTP_HEADER_REFER					= "Referer";
	public static final String	HTTP_HEADER_COOKIE				= "Cookie";
	public static final String	HTTP_HEADER_LOCATION				= "Location";
	public static final String	HTTP_HEADER_SET_COOKIE			= "Set-Cookie";
	public static final String HTTP_HEADER_CONTENT_TYPE = "Content-type";
	
	private static final String	DEF_ACCEPT							= "text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2";
	private static final String	DEF_ACCEPT_LANG					= "zh-CN";
	private static final String	DEF_ACCEPT_ENCODING				= "gzip, deflate";
	private static final String	DEF_CHARTSET						= "ISO8859-1";
	public static final String DEF_POST_CONTENT_TYPE = "application/x-www-form-urlencoded";
	
	//	x.setRequestHeader('Content-type','application/x-www-form-urlencoded');
	public static final String METHOD_GET						= "GET";
	public static final String METHOD_POST						= "POST";
	
	private static final boolean	DEF_FOLLOW_REDIRECT				= true;
	private static final int 			DEF_TIMEOUT  = 90000;
	
	private static String	DEF_UA								= "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.19 (KHTML, like Gecko) Ubuntu/12.04 Chromium/18.0.1025.168 Chrome/18.0.1025.168 Safari/535.19";
	
	public static StringBuffer Get(final String url, final String refer, final String cks) throws MalformedURLException, IOException{
		return Get(url,refer,cks,DEF_CHARTSET);
	}
	
	public static StringBuffer Get(final String url, final String refer, final CookieManager ckmgr) throws MalformedURLException, IOException{
		return Get(url,refer,ckmgr,DEF_CHARTSET);
	}
		
	public static StringBuffer Get(final String url, final String refer, final Object cks, final String charset) throws MalformedURLException, IOException{
		CookieManager ckmgr = null;
		if(cks instanceof CookieManager) {
			ckmgr = (CookieManager) cks;
		} else if (cks instanceof String){
			ckmgr = new CookieManager();
			ckmgr.setCookies(cks.toString());
		}
		HttpRequest http = new HttpRequest(ckmgr);
		http.open(METHOD_GET, url, refer);
		http.send(null);
		if (HttpURLConnection.HTTP_OK == http.getStatus()) {
			return http.getResponse(charset);
		}
		return null;
	}

	
	public static StringBuffer Post(final String url, final String refer, final Object cks, final String data, final String charset) throws MalformedURLException, IOException{
		return Post(url, refer, cks, data.getBytes(charset), charset, null);
	}
	
	public static StringBuffer Post(final String url, final String refer, final Object cks, final byte[] data, final String charset, final String content_type) throws MalformedURLException, IOException{
		CookieManager ckmgr = null;
		if(cks instanceof CookieManager) {
			ckmgr = (CookieManager) cks;
		} else if (cks instanceof String){
			ckmgr = new CookieManager();
			ckmgr.setCookies(cks.toString());
		}
		HttpRequest http = new HttpRequest(ckmgr);
		http.open(METHOD_POST, url, refer);
		http.send(data, content_type);
		if (HttpURLConnection.HTTP_OK == http.getStatus()) {
			return http.getResponse(charset);
		}
		return null;
	}

	
	public static byte[] GetContent(final String method, final String url, final String refer, final Object cks, final byte[] data) throws MalformedURLException, IOException {
		CookieManager ckmgr = null;
		if(cks instanceof CookieManager) {
			ckmgr = (CookieManager) cks;
		} else if (cks instanceof String){
			ckmgr = new CookieManager();
			ckmgr.setCookies(cks.toString());
		}
		HttpRequest http = new HttpRequest(ckmgr);
		http.open(method, url, refer);
		http.send(data);
		if (HttpURLConnection.HTTP_OK == http.getStatus()) {
			return http.getResponseBytes();
		}
		return null;
	}
	
	public static String getRedirectUrl(final String method, final String url, final String refer, final Object cks) throws MalformedURLException, IOException{
		CookieManager ckmgr = null;
		if(cks instanceof CookieManager) {
			ckmgr = (CookieManager) cks;
		} else if (cks instanceof String){
			ckmgr = new CookieManager();
			ckmgr.setCookies(cks.toString());
		}
		HttpRequest http = new HttpRequest(ckmgr);
		http.open(method, url, refer);
		http.conn.setInstanceFollowRedirects(false);
		http.send(null);
		return http.getRewriteLocation();
	}
	
	
	public HttpURLConnection		conn;
	public CookieManager			cookieMgr;

	public HttpRequest() {
		this.cookieMgr = new CookieManager();
	}

	public HttpRequest(final CookieManager cookie) {
		this.cookieMgr = cookie; ;
	}
	
	public HttpURLConnection open( final String method, final String url, final String refer) throws MalformedURLException, IOException {
		conn = (HttpURLConnection) new URL(url).openConnection();
		conn.setRequestMethod(method);
		conn.setInstanceFollowRedirects(DEF_FOLLOW_REDIRECT);
		conn.setAllowUserInteraction(false);
		conn.setReadTimeout(DEF_TIMEOUT);
		conn.setRequestProperty(HTTP_HEADER_ACCEPT, DEF_ACCEPT);
		conn.setRequestProperty(HTTP_HEADER_ACCEPT_LANG, DEF_ACCEPT_LANG);
		conn.setRequestProperty(HTTP_HEADER_UA, DEF_UA);
		conn.setRequestProperty(HTTP_HEADER_ACCEPT_ENCODING, DEF_ACCEPT_ENCODING);

		if (null != refer) {
			conn.setRequestProperty(HTTP_HEADER_REFER, refer);
		}
		return conn;
	}
	
	public static void setDEF_UA(final String ua){
		DEF_UA = ua;
	}
	
	public void setUA(final String v){
		conn.setRequestProperty(HTTP_HEADER_UA, v);
	}
	
	public void setAccept(final String v){
		conn.setRequestProperty(HTTP_HEADER_ACCEPT, v);
	}
	public void setAcceptLang(final String v){
		conn.setRequestProperty(HTTP_HEADER_ACCEPT_LANG, v);
	}
	public void setAcceptEncoding(final String v){
		conn.setRequestProperty(HTTP_HEADER_ACCEPT_ENCODING, v);
	}
	
	public void setRefer(final String v){
		conn.setRequestProperty(HTTP_HEADER_REFER, v);
	}
	
	public void setCookie(final String cks){
		if(null == cookieMgr) cookieMgr = new CookieManager();
		cookieMgr.setCookies(cks);
	}
	
	public void setFollowRedirect(final boolean b){
		conn.setInstanceFollowRedirects(b);
	}
	
	public void setTimeout(final int t){
		conn.setReadTimeout(t);
	}
	
	public void setContentType(String type) {
		conn.setRequestProperty(HTTP_HEADER_CONTENT_TYPE, type);
	}
	
	public void setHeader(String key, String val){
		conn.setRequestProperty(key, val);
	}

	public void send(final String data,final String charset) throws IOException{
		send(null == data ? null : data.getBytes(charset), null);
	}
	
	public void send(final String data, final String charset, final String content_type) throws IOException{
		send(null == data ? null : data.getBytes(charset), content_type);
	}

	public void send(final byte[] postdata) throws IOException{
		send(postdata, null);
	}
	
	public void send(final byte[] postdata, final String content_type) throws IOException{
		if(null == conn) return ;
		
		if (null != cookieMgr) {
			conn.setRequestProperty(HTTP_HEADER_COOKIE, cookieMgr.getCookies());
		}
		if(null != content_type) {
			conn.setRequestProperty(HTTP_HEADER_CONTENT_TYPE, content_type);
		}
		
		conn.setDoInput(true);
		conn.setDoOutput(null != postdata);
		conn.connect();

		if (null != postdata) {
			conn.getOutputStream().write(postdata);
			conn.getOutputStream().close();
		}

		final Map<String, List<String>> headers = conn.getHeaderFields();
		final List<String> cookies = headers.get(HTTP_HEADER_SET_COOKIE);
		if (null != cookies && null != cookieMgr) {
			for (final String ckstr : cookies) {
				cookieMgr.setCookie(ckstr);
			}
		}
	}
	
	public StringBuffer getResponse() throws IOException{
		return getResponse(DEF_CHARTSET);
	}
	
	public StringBuffer getResponse(String charsetname) throws IOException{
		String charset = conn.getContentType();
		if (null != charset) {
			final int i = charset.indexOf("charset=");
			if (i > 1) {
				charset = charset.substring(i + "charset=".length());
			} else {
				charset = (null == charsetname) ? DEF_CHARTSET : charsetname;
			}
		} else {
			charset = (null == charsetname) ? DEF_CHARTSET : charsetname;
		}

		InputStream in = conn.getInputStream();
		if ("gzip".equals(conn.getContentEncoding())) {
			in = new GZIPInputStream(in);
		} else if ("deflate".equals(conn.getContentEncoding())) {
			in = new InflaterInputStream(in);
		}
		final BufferedReader br = new BufferedReader(new InputStreamReader(in, charset));

		StringBuffer data = new StringBuffer();
		char[] buffer = new char[4096];
		 
		int c = br.read(buffer) ;
		while (c >=0 ) {
			data.append(buffer, 0, c); 
			c = br.read(buffer) ;
		}
		br.close();
		in.close();
		conn.disconnect();
		return data;
		
	}
	
	public byte[] getResponseBytes() throws IOException{
		InputStream in = conn.getInputStream();
		if ("gzip".equals(conn.getContentEncoding())) {
			in = new GZIPInputStream(in);
		}
		ByteArrayOutputStream  out = new ByteArrayOutputStream();
		int b = -1;
		while(-1 != ( b = in.read())) {
			out.write(b);
		}
		in.close();
		out.close();
		conn.disconnect();
		return out.toByteArray();
	}

	public int getStatus() throws IOException{
		return conn.getResponseCode();
	}
	
	public String getRewriteLocation(){
		final Map<String, List<String>> headers = conn.getHeaderFields();
		final List<String> locations = headers.get(HTTP_HEADER_LOCATION);
		if (null != locations) {
			return locations.get(locations.size() - 1);
		}
		return null;
	}
	
	public HttpURLConnection getConnection(){
		return conn;
	}
	
	public CookieManager getCookieManager(){
		return cookieMgr;
	}
	
	public String getContentType(){
		if ( null == conn ) return null;
		return conn.getContentType();
	}
	
	public Map<String, List<String>> getHeaderFields(){
		if ( null == conn ) return null;
		return conn.getHeaderFields();
	}	

	public String getContentEncoding(){
		if ( null == conn ) return null;
		return conn.getContentEncoding();
	}	
	
	public String getHeaderField(final String key){
		if ( null == conn ) return null;
		return conn.getHeaderField(key);
	}	
	
	public int getContentLength(){
		if ( null == conn ) return -1;
		return conn.getContentLength();
	}
	
	public boolean isOk() {
		if (null == conn) return false;
		try {
			return HttpURLConnection.HTTP_OK == conn.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	@Override
	protected void finalize() throws Throwable {
		cookieMgr = null;
		if(null != conn) {
			conn.disconnect();
			conn = null;
		}
	}

	public static void main(String[] args) throws Exception{
		System.out.println(
				Post("http://www.lusen.com/MallService.axd",
						"http://www.lusen.com/Product/Review.aspx?pId=4741&gId=3029",
						null,
						"<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><action>AjaxPageData</action><PageIndex>1</PageIndex><PageSize>5</PageSize><Module>Comments</Module><Score>0</Score><ProductId>4741</ProductId><GoodsId>3029</GoodsId></root>",
						"utf-8"
						)
				);
		System.out.println(getRedirectUrl("GET", "http://kuaibao.qq.com/s/20170717A00JQV00?titleFlag=2", "", null));
//		System.out.println(getRedirectUrl("GET", "http://bbs.hexun.com/news/connect.aspx?newsId=140831797&bid=153", "", null));
	}
	
}
