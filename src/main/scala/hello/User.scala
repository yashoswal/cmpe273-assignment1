package hello
import scala.beans._
import java.util.concurrent.atomic.AtomicLong
import javax.validation.constraints.NotNull
object User {

}
//package main.scala

class User(val email : String, @NotNull val pass: String) {

  //private var user_id: Long = uid
  private var User_Id : String = null;
  @NotNull
  private var user_email  = email  
  @NotNull
  private var user_password: String = pass
  var cardmap : Map[String, Card] = Map()
  var webmap : Map[String, Web] = Map()
  var bankmap : Map[String, Bank] = Map()
  private var cardcounter = 0
  private var bankcounter = 0
  private var webcounter = 0
  var card = new Card("testName", "333", "12.324.5")
  var web = new Web("testName", "333", "12.324.5")
  var bank = new Bank("testName", "333", "12.324.5")
  private var created_at : String = null
  def this()={
    this(null,null)
  }
  
  //def getUser_id = user_id
  //def setUser_id(newId: Long) = user_id = newId
  def getUserId : String = (return User_Id)
  def getUser_password : String = { return user_password}
  def getUser_email : String = { return user_email}
  def getcreated_at : String = {return created_at}
  def setcreated_at(currenttime : String) : Unit = {created_at = currenttime}
  def setUser_email(email : String) : Unit = {this.user_email = email}
  def setUser_Pass(pass : String) : Unit = {this.user_password = pass}
  def setUserId (Id : String): Unit = {User_Id = Id}
 // def getUserId : String = (return User_Id)
  def makecardmap (usercard : Card) : Card = {
    this.card = usercard
	card.setcardNumber(usercard.getcardnumber)
    this.cardcounter += 1
    var cardId = "c-" + this.cardcounter.toString()
    card.setcardId(cardId)
    this.cardmap = cardmap + (cardId -> card)
    return this.card
  }
  
  def makewebmap (userweb : Web) : Web = {
    web = userweb
	//web.setcardNumber(usercard.getcardnumber)
    this.webcounter += 1
    var webId = "l-" + webcounter.toString()
    web.setwebId(webId)
    webmap = webmap + (webId -> web)
    return web
  }
  
  def makebankmap (userbank : Bank) : Bank = {
    bank = userbank
	//web.setcardNumber(usercard.getcardnumber)
    this.bankcounter += 1
    var bankId = "b-" + bankcounter.toString()
    bank.setbankId(bankId)
    bankmap = bankmap + (bankId -> bank)
    return bank
  }
  //def getcardmap : Map[String, Card] = {return cardmap }
  //def setUser_password(newName: String) = user_password = newName

}