package SHOPPING;

// 판매자 테이블
public class GSeller {

	// 필드

	// seller

	private String gd_name;
	private int gd_code;
	private int gd_kind;
	private int gd_price;
	private int gd_stock;
	private String gd_size;
	private String gd_color;

	// 회원가입
	private String in_name;
	private String in_id;
	private int pw;
	private String birth;
	private String email;
	private String phone;

	// 구매
	private String od_id;
	private int od_code;
	private String addr;
	private String bank;
	private long bankNo;

	// getter , setter
	public String getGd_size() {
		return gd_size;
	}

	public void setGd_size(String gd_size) {
		this.gd_size = gd_size;
	}

	public String getGd_color() {
		return gd_color;
	}

	public void setGd_color(String gd_color) {
		this.gd_color = gd_color;
	}

	public String getGd_name() {
		return gd_name;
	}

	public void setGd_name(String gd_name) {
		this.gd_name = gd_name;
	}

	public int getGd_code() {
		return gd_code;
	}

	public void setGd_code(int gd_code) {
		this.gd_code = gd_code;
	}

	public int getGd_kind() {
		return gd_kind;
	}

	public void setGd_kind(int gd_kind) {
		this.gd_kind = gd_kind;
	}

	public int getGd_price() {
		return gd_price;
	}

	public void setGd_price(int gd_price) {
		this.gd_price = gd_price;
	}

	public int getGd_stock() {
		return gd_stock;
	}

	public void setGd_stock(int gd_stock) {
		this.gd_stock = gd_stock;
	}

	public String getIn_name() {
		return in_name;
	}

	public void setIn_name(String in_name) {
		this.in_name = in_name;
	}

	public String getIn_id() {
		return in_id;
	}

	public void setIn_id(String in_id) {
		this.in_id = in_id;
	}

	public int getPw() {
		return pw;
	}

	public void setPw(int pw) {
		this.pw = pw;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOd_id() {
		return od_id;
	}

	public void setOd_id(String od_id) {
		this.od_id = od_id;
	}

	public int getOd_code() {
		return od_code;
	}

	public void setOd_code(int od_code) {
		this.od_code = od_code;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public long getBankNo() {
		return bankNo;
	}

	public void setBankNo(long bankNo) {
		this.bankNo = bankNo;
	}

	// toString
	@Override
	public String toString() {
		return "GSeller [gd_name=" + gd_name + ", gd_code=" + gd_code + ", gd_kind=" + gd_kind + ", gd_price="
				+ gd_price + ", gd_size=" + gd_size + ", gd_color=" + gd_color + ", in_name=" + in_name + ", in_id="
				+ in_id + ", pw=" + pw + ", birth=" + birth + ", email=" + email + ", phone=" + phone + ", od_id="
				+ od_id + ", od_code=" + od_code + ", addr=" + addr + ", bank=" + bank + ", bankNo=" + bankNo + "]";
	}

	// constructor
	public GSeller() {
		super();
	}

	public GSeller(String gd_name, int gd_code, int gd_kind, int gd_price, String gd_size, String gd_color,
			String in_name, String in_id, int pw, String birth, String email, String phone, String od_id, int od_code,
			String addr, String bank, Long bankNo) {
		super();
		this.gd_name = gd_name;
		this.gd_code = gd_code;
		this.gd_kind = gd_kind;
		this.gd_price = gd_price;
		this.gd_size = gd_size;
		this.gd_color = gd_color;
		this.in_name = in_name;
		this.in_id = in_id;
		this.pw = pw;
		this.birth = birth;
		this.email = email;
		this.phone = phone;
		this.od_id = od_id;
		this.od_code = od_code;
		this.addr = addr;
		this.bank = bank;
		this.bankNo = bankNo;
	}

}