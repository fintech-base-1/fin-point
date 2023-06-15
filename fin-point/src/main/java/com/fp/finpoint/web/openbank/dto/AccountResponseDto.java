package com.fp.finpoint.web.openbank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDto {
    private String api_tran_id;
    private String rsp_code;
    private String rsp_message;
    private String api_tran_dtm;
    private String user_name;
    private String res_cnt;
    List<Account> res_list;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Account {
        public String fintech_use_num;
        public String account_alias;
        public String bank_code_std;
        public String bank_code_sub;
        public String bank_name;
        public String account_num_masked;
        public String account_holder_name;
        public String account_holder_type;
        public String inquiry_agree_yn;
        public String inquiry_agree_dtime;
        public String transfer_agree_yn;
        public String transfer_agree_dtime;
        public String account_state;
        public String savings_bank_name;
        public String account_seq;
        public String account_type;
    }
}
