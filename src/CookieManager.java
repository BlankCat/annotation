/**
 * 
 * @author  barenx@gmail.com copy all right
 *
 */
public class CookieManager {

	private final java.util.Map<String, String>	ckdata;

	public CookieManager() {
		ckdata = new java.util.HashMap<String, String>();
	}

	public CookieManager(final java.util.Map<String, String> cks) {
		ckdata = cks;
	}

	public CookieManager(final String ckstr) {
		this();
		this.setCookie(ckstr);
	}

	public String getCookie(String key){
		if(null == key) return null;
		return ckdata.get(key);
	}
	
	public String getCookies() {
		final StringBuffer sbf = new StringBuffer();
		for (final java.util.Map.Entry<String, String> ent : ckdata.entrySet()) {
			sbf.append(ent.getKey());
			sbf.append("=");
			sbf.append(ent.getValue());
			sbf.append("; ");
		}
		final int n = sbf.length();
		if (n > 0) {
			sbf.setLength(n - 1);
		}
		return sbf.toString();
	}

	public void removeCookie(final String key) {
		ckdata.remove(key);
	}

	public void reset() {
		ckdata.clear();
	}

	/**
	 * set cookie <br>
	 * eg: <br>
	 * superuin=o0187191630; PATH=/; DOMAIN=ptlogin2.qq.com; HttpOnly
	 * @param ckstr
	 */
	public void setCookie(final String ckstr) {
		final int i = ckstr.indexOf('=');
		if (i < 0) {
			return;
		}
		int j = ckstr.indexOf(';');
		if (j < 0) {
			j = ckstr.length();
		}
		ckdata.put(ckstr.substring(0, i), ckstr.substring(i + 1, j));
	}

	public void setCookie(final String key, final String value) {
		ckdata.put(key, value);
	}

	/**
	 * set cookies<br />
	 * eg: <br>
	 * pgv_pvid=4611198827; ptisp=cnc; chkuin=187191630; confirmuin=187191630; ptui_loginuin=187191630
	 * @param cks
	 */
	public void setCookies(final String cks) {
		int i = 0;
		while (i >= 0) {
			final int p = cks.indexOf('=', i);
			if (p < 0) {
				return;
			}
			int q = cks.indexOf("; ", p);
			if (q < 0) {
				q = cks.length();
			}
			ckdata.put(cks.substring(i, p), cks.substring(p + 1, q));
			i = q + 2;
		}
	}

	@Override
	public String toString() {
		return getCookies();
	}
}
