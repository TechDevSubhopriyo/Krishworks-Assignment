function doGet(request) {
  var ss = SpreadsheetApp.openById("1A1NHKjWQ0KVrHKO3uEKtRw4N2LS2-V_Qc9N0fLs6Z3o");
  var s_sheet = ss.getSheetByName("Data");

  var t_sheet = ss.getSheetByName("Results");
  if(t_sheet==null){
    ss.insertSheet("Results");
    t_sheet = ss.getSheetByName("Results");
  }
  t_sheet.clear();

  var source = s_sheet.getDataRange().getValues();

  for(i in source){

    if(i==0)
      t_sheet.appendRow([source[i][0],source[i][1],source[i][2],"Status"]);
    else{
      if(source[i][2]<40){
        t_sheet.appendRow([source[i][0],source[i][1],source[i][2],"Fail"]);
      }
      else{
        t_sheet.appendRow([source[i][0],source[i][1],source[i][2],"Pass"]);
      }
    }
  }
  return ContentService.createTextOutput(JSON.stringify({"result":true})).setMimeType(ContentService.MimeType.JSON);
}
