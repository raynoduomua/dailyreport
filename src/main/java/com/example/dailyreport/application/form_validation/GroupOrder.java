package com.example.dailyreport.application.form_validation;

import javax.validation.GroupSequence;

/**
 * バリデーションの順番を設定
 * チェックの順番
 * 1.ValidGroup1
 * 2.ValidGroup2
 */
@GroupSequence({ ValidGroup1.class, ValidGroup2.class, ValidGroup3.class })
public interface GroupOrder {

}
