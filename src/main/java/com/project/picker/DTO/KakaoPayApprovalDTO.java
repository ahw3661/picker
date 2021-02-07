package com.project.picker.DTO;

import java.util.Date;

public class KakaoPayApprovalDTO {

	//response
    private String aid, tid, cid, sid; //요청고유번호, 결제고유번호, 가맹점코드, 정기결제용ID
    private String partner_order_id, partner_user_id, payment_method_type; // 가맹점 주문번호, 가맹점 회원id, 결제수단
    private AmountDTO amount; // 결제금액정보
    private CardDTO card_info; // 결제상세정보(결제수단이 카드일 경우)
    private String item_name; // 상품이름
    private String item_code, payload; // 상품코드, 결제승인요청에  대해 저장한 값(요청시 전달된내용)
    private Integer quantity; // 상품수량
    private Integer tax_free_amount, vat_amount; // 비과세금액, 부가세금액
    private Date created_at, approved_at; // 결제준비 요청시각, 결제 승인 시각
    
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getPartner_order_id() {
		return partner_order_id;
	}
	public void setPartner_order_id(String partner_order_id) {
		this.partner_order_id = partner_order_id;
	}
	public String getPartner_user_id() {
		return partner_user_id;
	}
	public void setPartner_user_id(String partner_user_id) {
		this.partner_user_id = partner_user_id;
	}
	public String getPayment_method_type() {
		return payment_method_type;
	}
	public void setPayment_method_type(String payment_method_type) {
		this.payment_method_type = payment_method_type;
	}
	public AmountDTO getAmount() {
		return amount;
	}
	public void setAmount(AmountDTO amount) {
		this.amount = amount;
	}
	public CardDTO getCard_info() {
		return card_info;
	}
	public void setCard_info(CardDTO card_info) {
		this.card_info = card_info;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getItem_code() {
		return item_code;
	}
	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getTax_free_amount() {
		return tax_free_amount;
	}
	public void setTax_free_amount(Integer tax_free_amount) {
		this.tax_free_amount = tax_free_amount;
	}
	public Integer getVat_amount() {
		return vat_amount;
	}
	public void setVat_amount(Integer vat_amount) {
		this.vat_amount = vat_amount;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getApproved_at() {
		return approved_at;
	}
	public void setApproved_at(Date approved_at) {
		this.approved_at = approved_at;
	}
    
}
