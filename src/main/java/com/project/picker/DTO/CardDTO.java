package com.project.picker.DTO;

public class CardDTO {

	private String purchase_corp, purchase_corp_code; // 매입카드사 한글명, 매입카드사 코드
    private String issuer_corp, issuer_corp_code; // 카드발급사 한글명, 카드 발급사 코드
    private String bin, card_type, install_month, approved_id, card_mid; // 카드 BIN, 카드타입, 할부개월수, 카드사 승인번호, 카드사 가맹점 번호
    private String interest_free_install, card_item_code; // 무이자할부여부, 카드상품코드
	
    public String getPurchase_corp() {
		return purchase_corp;
	}
	public void setPurchase_corp(String purchase_corp) {
		this.purchase_corp = purchase_corp;
	}
	public String getPurchase_corp_code() {
		return purchase_corp_code;
	}
	public void setPurchase_corp_code(String purchase_corp_code) {
		this.purchase_corp_code = purchase_corp_code;
	}
	public String getIssuer_corp() {
		return issuer_corp;
	}
	public void setIssuer_corp(String issuer_corp) {
		this.issuer_corp = issuer_corp;
	}
	public String getIssuer_corp_code() {
		return issuer_corp_code;
	}
	public void setIssuer_corp_code(String issuer_corp_code) {
		this.issuer_corp_code = issuer_corp_code;
	}
	public String getBin() {
		return bin;
	}
	public void setBin(String bin) {
		this.bin = bin;
	}
	public String getCard_type() {
		return card_type;
	}
	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}
	public String getInstall_month() {
		return install_month;
	}
	public void setInstall_month(String install_month) {
		this.install_month = install_month;
	}
	public String getApproved_id() {
		return approved_id;
	}
	public void setApproved_id(String approved_id) {
		this.approved_id = approved_id;
	}
	public String getCard_mid() {
		return card_mid;
	}
	public void setCard_mid(String card_mid) {
		this.card_mid = card_mid;
	}
	public String getInterest_free_install() {
		return interest_free_install;
	}
	public void setInterest_free_install(String interest_free_install) {
		this.interest_free_install = interest_free_install;
	}
	public String getCard_item_code() {
		return card_item_code;
	}
	public void setCard_item_code(String card_item_code) {
		this.card_item_code = card_item_code;
	}
	
}
