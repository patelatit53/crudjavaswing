package asw;


import javax.jws.WebService;

@WebService
public class hello {
	
	public void constructor(){
		
	}
public String hello1(String s){
	return "hello"+s;
}
}