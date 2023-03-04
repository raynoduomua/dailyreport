package com.example.dailyreport.application.form_validation;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AccountForm {

	@NotBlank(groups = ValidGroup1.class)
	private String name;

	@NotBlank(groups = ValidGroup1.class)
	@Pattern(regexp = "^[ァ-ヶ]*$", groups = ValidGroup2.class)
	private String nameKana;

	@NotBlank(groups = ValidGroup1.class)
	@Pattern(regexp = "^[a-zA-Z_]+$", groups = ValidGroup2.class)
	private String loginId;

	@NotBlank(groups = ValidGroup1.class)
	@Size(min = 8, max = 100, groups = ValidGroup2.class)
	@Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup2.class)
	private String password;

	@NotBlank(groups = ValidGroup1.class)
	@Size(min = 8, max = 100, groups = ValidGroup2.class)
	@Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup2.class)
	private String passwordconfirm;

	@NotNull(groups = ValidGroup1.class)
	private Integer clientNameId;

	@NotNull(groups = ValidGroup1.class)
	private Integer courseNameId;

	@NotNull(groups = ValidGroup1.class)
	private Integer role;

	@AssertTrue(message = "パスワード と パスワード再入力 は同一にしてください。", groups = ValidGroup3.class)
	public boolean isPasswordValid() {
		if (password == null || password.isEmpty()) {
			return true;
		}

		return password.equals(passwordconfirm);
	}

}
