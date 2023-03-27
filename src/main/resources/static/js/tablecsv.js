$(function() {
  $("#table-csv").dataTable({
    // DataTablesを日本語化する
    language: {
      url: "/webjars/datatables-plugins/i18n/ja.json"
    },
    // 各種ボタンを有効化する
    dom: "Bfrtip",
    buttons: ["csvHtml5"],
    //絞り込み検索
    searching:false,
    // 件数のデフォルトの値を50にする
    displayLength: 50
  });
});
