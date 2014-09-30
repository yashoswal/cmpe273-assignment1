package hello
//import pojo.User
//import java.lang.Object._
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype._
import org.springframework.boot.autoconfigure._
import org.springframework.web.bind.annotation._
import java.util.concurrent.atomic.AtomicLong
import org.springframework.web.bind.annotation.{ResponseBody, RequestMapping, RequestParam, RestController}
//import GreetingController._
//import java.util._
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter
import org.springframework.http.HttpStatus
import collection.JavaConversions._
import java.util.ArrayList
import collection.JavaConversions._
import javax.validation.Valid                          // import this for validation
import org.springframework.validation.BindingResult
import org.joda.time.DateTime
//import hello.User
//import hello.Greeting
//object GreetingController {

 //private val template = "Hello, %s!"
//}
@RestController
@Configuration
@EnableAutoConfiguration
@ComponentScan
class WalletController {

  var counter1 = 0//new AtomicLong()
   var  user=new User();
	var user_map: Map[String, User] = Map();
	//var userlist: ArrayList[User, Int] = new ArrayList[User]();
	//var user_map=new Map[String,User];
  @RequestMapping(value = Array("/api/V1/users"), method = Array(RequestMethod.POST), headers = Array("content-type=application/json"), consumes = Array("application/json"))
        //@ResponseBody 
       // @ResponseStatus(HttpStatus.CREATED)
        def userCreation(@Valid @RequestBody user : User, result: BindingResult):User = {
    
    if (result.hasErrors()) {
      throw new ParameterMissingException(result.toString)
    } 
    else 
    {            
    //def userCreation(@RequestParam(value = "user_email", required = false) email : String,@RequestParam(value = "user_password", required = true) password : String) : User = {
        		//new User(counter.incrementAndGet(),email,password)
    counter1 = counter1+1
   // this.userlist.add(user)
    this.user = user
   // var user_map: Map[Int, Object] = Map();
  	//user_map.add(counter1, user) //+ (counter1 -> user)
  	//println("Hi") 
    var c = "U-" + counter1.toString()
    user.setUserId(c)
    val currentTime = DateTime.now;
    this.user.setcreated_at(currentTime.toString)
    this.user_map = this.user_map + (c -> this.user)
   //println("keyset:"+user_map.keys )
   //println("keyset:"+user_map.values )
    this.user_map.keys.foreach{i =>
     						print("Key " + i)
     						println("Values " + user_map(i))
   }}
  	//if(user_map.contains(1)){println("yes")}
   return user
   
}
  @RequestMapping(value=Array("/api/V1/users/{userid}"), method=Array(RequestMethod.GET), produces = Array("application/json"), headers=Array("content-type=application/json"))
	def viewuser(@PathVariable("userid")  userId:String ):User={
		//println("in get")
		//var u = new User()
		var u = user_map(userId)
		//u.setUser_email("sdj")
		//u.setUser_Pass("pass")
		//list = user_map(userId)
		//var u  = list.get(0)
       println(""+ u.getUser_email);
		return u;
	} 
    @RequestMapping(value = Array("/api/V1/users/{userid}"), method = Array(RequestMethod.PUT), headers = Array("content-type=application/json"), consumes = Array("application/json"))
		def upduser(@PathVariable("userid")  userId:String ,@RequestBody user : User ):User={
      var u = new User()
		//u.setUser_email("sdj")
		//u.setUser_Pass("pass")
		//u = user_map(userId)
		//u.setUser_email(this.user.getUser_email)
		//u.setUser_Pass(this.user.getUser_password)
		user.setUserId(userId)
      this.user_map = this.user_map + (userId -> user)
		u = user_map(userId)
		println(u.getUser_email)
      return user
		
    }
    
   @RequestMapping(value = Array("/api/V1/users/{userid}/idcards"), method = Array(RequestMethod.POST), headers = Array("content-type=application/json"), consumes = Array("application/json"))
       def idcards( @PathVariable("userid")  userId:String ,@Valid @RequestBody usercard : Card,result: BindingResult): Card = {
    		    if (result.hasErrors()) {
      throw new ParameterMissingException(result.toString)
    } 
    else 
    {  var u = user_map(userId)
    		   //var counter = 0
    		   u.makecardmap(usercard)
    		   this.user_map += (userId -> u)
    		   return u.card
    }
    		   //this.user.cardmap = user.cardmap + (j -> usercard)
   	   //return u.card
       
     }
   @RequestMapping(value=Array("/api/V1/users/{userid}/idcards"), method=Array(RequestMethod.GET), produces = Array("application/json"), headers=Array("content-type=application/json"))
	def viewcards(@PathVariable("userid")  userId:String ):java.util.Map[String,Card]={
    var u = user_map(userId)
    return u.cardmap//.keys.foreach {i => return u.getcardmap(i)}
   }
   
   @RequestMapping(value=Array("/api/V1/users/{userid}/idcards/{card_id}"), method=Array(RequestMethod.DELETE), headers=Array("content-type=application/json"))@ResponseStatus(HttpStatus.NO_CONTENT)
	def deletecards(@PathVariable("userid")  userId:String, @PathVariable("card_id")  cardId:String ):Unit={
     var u = user_map(userId)
     u.cardmap -= cardId
     user_map += (userId -> u)
   }
   
   @RequestMapping(value = Array("/api/V1/users/{userid}/weblogins"), method = Array(RequestMethod.POST), headers = Array("content-type=application/json"), consumes = Array("application/json"))
       def webloginscards( @PathVariable("userid")  userId:String ,@Valid @RequestBody userlogin : Web,result: BindingResult): Web = {
    if (result.hasErrors()) {
      throw new ParameterMissingException(result.toString)
    } 
    else 
    {
     var u = user_map(userId)
    		   //var counter = 0
    		   u.makewebmap(userlogin)
    		   this.user_map += (userId -> u)
    		   //this.user.cardmap = user.cardmap + (j -> usercard)
    		   return u.web
    }  
     }
   @RequestMapping(value=Array("/api/V1/users/{userid}/weblogins"), method=Array(RequestMethod.GET), produces = Array("application/json"), headers=Array("content-type=application/json"))
	def viewweb(@PathVariable("userid")  userId:String ):java.util.Map[String,Web]={
    var u = user_map(userId)
    return u.webmap//.keys.foreach {i => return u.getcardmap(i)}
   }
   
   @RequestMapping(value=Array("/api/V1/users/{userid}/weblogins/{login_id}"), method=Array(RequestMethod.DELETE), headers=Array("content-type=application/json"))@ResponseStatus(HttpStatus.NO_CONTENT)
	def deleteweb(@PathVariable("userid")  userId:String, @PathVariable("login_id")  loginId:String ):Unit={
     var u = user_map(userId)
     u.webmap -= loginId
     user_map += (userId -> u)
   }
   
    @RequestMapping(value = Array("/api/V1/users/{userid}/bankaccounts"), method = Array(RequestMethod.POST), headers = Array("content-type=application/json"), consumes = Array("application/json"))
       def userbank( @PathVariable("userid")  userId:String ,@Valid @RequestBody bank : Bank,result: BindingResult): Bank = {
    if (result.hasErrors()) {
      throw new ParameterMissingException(result.toString)
    } 
    else 
    {
      var u = user_map(userId)
    		   //var counter = 0
    		   u.makebankmap(bank)
    		   this.user_map += (userId -> u)
    		   //this.user.cardmap = user.cardmap + (j -> usercard)
    		   return u.bank
    }  
     }
   @RequestMapping(value=Array("/api/V1/users/{userid}/bankaccounts"), method=Array(RequestMethod.GET), produces = Array("application/json"), headers=Array("content-type=application/json"))
	def viewbank(@PathVariable("userid")  userId:String ):java.util.Map[String,Bank]={
    var u = user_map(userId)
    return u.bankmap//.keys.foreach {i => return u.getcardmap(i)}
   }
   
   @RequestMapping(value=Array("/api/V1/users/{userid}/bankaccounts/{ba_id}"), method=Array(RequestMethod.DELETE), headers=Array("content-type=application/json"))@ResponseStatus(HttpStatus.NO_CONTENT)
	def deletebank(@PathVariable("userid")  userId:String, @PathVariable("ba_id")  baId:String ):Unit={
     var u = user_map(userId)
     u.bankmap -= baId
     user_map += (userId -> u)
   }
}
   //@RequestMapping(value = Array("/users"), method = Array(RequestMethod.POST), headers = Array("content-type=application/json"), consumes = Array("application/json"))
       // @ResponseBody 
       // @ResponseStatus(HttpStatus.CREATED)
      
        

