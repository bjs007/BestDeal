public class Account {
  String userid;
  String Name;
  String cardNum;
  String cardType;
  String email;
  String phone;

  Account(String userid,String name,String cardNum,String email,String phone,String cardType){
    this.userid = userid;
    this.Name = name;
    this.cardNum = cardNum;
    this.cardType = cardType;
    this.email = email;
    this.phone = phone;
  }

  String getuserid(){
    return userid;
  }
  String getName(){
    return Name;
  }
  String getcardNum(){
    return cardNum;
  }
  String getcardType(){
    return cardType;
  }
  String getemail(){
    return email;
  }
  String getphone(){
    return phone;
  }
}
