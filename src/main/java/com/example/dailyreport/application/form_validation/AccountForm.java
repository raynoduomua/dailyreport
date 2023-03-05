package com.example.dailyreport.application.form_validation;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.dailyreport.application.form_validation.unique.UniqueLoginID;

import lombok.Data;

@Data
public class AccountForm {

	@NotBlank(groups = ValidGroup1.class)
	private String name;

	@NotBlank(groups = ValidGroup1.class)
	@Pattern(regexp = "^[ァ-ヶ]*$", groups = ValidGroup2.class, message = "全角カナで入力してください")
	private String nameKana;

	@NotBlank(groups = ValidGroup1.class)
	@Pattern(regexp = "^[a-zA-Z_]+$", groups = ValidGroup2.class, message = "半角英字小文字大文字、_(アンダーバー)のみで入力してください")
	@UniqueLoginID(groups = ValidGroup3.class)
	private String loginId;

	@NotBlank(groups = ValidGroup1.class)
	@Size(min = 8, max = 100, groups = ValidGroup2.class, message = "パスワードは8～100文字以内で入力してください")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup2.class)
	private String password;

	@NotBlank(groups = ValidGroup1.class)
	@Size(min = 8, max = 100, groups = ValidGroup2.class, message = "パスワードは8～100文字以内で入力してください")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup2.class)
	private String passwordconfirm;

	@NotNull(groups = ValidGroup1.class, message = "クライアント名を選択してください")
	private Integer clientNameId;

	@NotNull(groups = ValidGroup1.class, message = "講座名を選択してください")
	private Integer courseNameId;

	@NotNull(groups = ValidGroup1.class, message = "権限を選択してください")
	private Integer role;

	@AssertTrue(message = "パスワード と パスワード再確認用 は同一にしてください。", groups = ValidGroup3.class)
	public boolean isPasswordValid() {
		if (password == null || password.isEmpty()) {
			return true;
		}

		return password.equals(passwordconfirm);
	}

}
