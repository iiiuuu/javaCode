
﻿
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CheckListUtil {
	/**判断多条数据是否重叠包含区间重叠
 	*@prama list：已存在的数据，et：需要判断的数据
 	*@return Boolean 是否重叠
	**/
	  public Boolean Ispass(List<SysCheckList> list,SysCheckList et){
		  Boolean result=true;
		  Set<String> VClist=new HashSet<String>();//签证类型
		  Set<String> POlist=new HashSet<String>();//入境方式
		  Set<String> AVTlist=new HashSet<String>();//入境次数
		  Set<String> AVVlist=new HashSet<String>();//有效期
		  Set<String> AMSDlist=new HashSet<String>();//停留期
		  Set<String> JTlist=new HashSet<String>();//职业
		  Set<String> PNClist=new HashSet<String>();//国籍
		  Set<String> VClistN=et.getVisaCategory();//签证类型
		  Set<String> POlistN=et.getProcessOption();//入境方式
		  Set<String> AVTlistN=et.getApplyVisaTimes();//入境次数
		  Set<String> AVVlistN=et.getApplyVisaValidity();//有效期
		  Set<String> AMSDlistN=et.getApplyMaxStayDays();//停留期
		  Set<String> JTlistN=et.getJobType();//职业
		  Set<String> PNClistN=et.getPersoninfoNationalityCountry();//国籍
		  int vcStatus=0;
		  int poStatus=0;
		  int avtStatus=0;
		  int avvStatus=0;
		  int amsdStatus=0;
		  int jtStatus=0;
		  int pncStatus=0;
		  //首先从数据库中的每个字段开始判断,只要输入的字段中，其中有一个字段不与数据库中的字段重复，则需要继续进行外层循环，否则跳出循环找到
		  if(list.size()!=0){
		 //判断每组数据
	    	for(SysCheckList l:list){
	    		//给予数据
	    		VClist.clear();
	    	 	POlist.clear();
	    	 	AVTlist.clear();
	    	 	AVVlist.clear();
	    	 	AMSDlist.clear();
	    	 	JTlist.clear();
	    	 	PNClist.clear();
			Boolean Vcresult=true;
			Boolean Poresult=true;
			Boolean Avtresult=true;
			Boolean Avvresult=true;
			Boolean Amsdresult=true;
			Boolean jtresult=true;
			Boolean pncresult=true; 
	    	 	if(l.getVisaCategory()!=null){
					  VClist.addAll(l.getVisaCategory());
				 }else{
					  vcStatus=1;
				 }
				  if(l.getProcessOption()!=null){
					  POlist.addAll(l.getProcessOption());
				  }else{
					  poStatus=1;
				  }
				  if(l.getApplyVisaTimes()!=null){
					  AVTlist.addAll(l.getApplyVisaTimes());
				  }else{
					  avtStatus=1;
				  }
				  if(l.getApplyVisaValidity()!=null){
					  AVVlist.addAll(l.getApplyVisaValidity());
				  }else{
					  avvStatus=1;
				  }
				  if(l.getApplyMaxStayDays()!=null){
					  AMSDlist.addAll(l.getApplyMaxStayDays());
				  }else{
					  amsdStatus=1;
				  }
				  if(l.getJobType()!=null){
					  JTlist.addAll(l.getJobType());
				  }else{
					  jtStatus=1;
				  } 
				  if(l.getPersoninfoNationalityCountry()!=null){
					  PNClist.addAll(l.getPersoninfoNationalityCountry());
				  }else{
					  pncStatus=1;
				  } 
				  if(vcStatus==1){
						  Vcresult=false;
				  }else{
					  if(VClistN==null){
						  Vcresult=false;
					  }else{
						  for(String vcN:VClistN){
							  for(String vc:VClist){
								  if(vcN.equals(vc)){
									  Vcresult=false;
									  break;
								  }
							  }
							  if(!Vcresult){
								  break;
							  }
						  }
					  }
				  }
				  if(poStatus==1){
						  Poresult=false;
				  }else{
					  if(POlistN==null){
						  Poresult=false;
					  }else{
						  for(String poN:POlistN){
							  for(String po:POlist){
								  if(poN.equals(po)){
									  Poresult=false;
									  break;
								  }
							  }
							  if(!Poresult){
								  break;
							  }
						  }
					  }
				  }
				  if(avtStatus==1){
						  Avtresult=false;
				  }else{
					  if(AVTlistN==null){
						  Avtresult=false;
					  }else{
						  for(String vcN:AVTlistN){
							  for(String vc:AVTlist){
								  if(vcN.equals(vc)){
									  Avtresult=false;
									  break;
								  }
							  }
							  if(!Avtresult){
								  break;
							  }
						  }
					  }
				  }
				  //有效期
				  if(avvStatus==1){
						  Avvresult=false;
				  }else{
					  if(AVVlistN==null){
						  Avvresult=false;
					  }else{
						  for(String vcN:AVVlistN){
							  for(String vc:AVVlist){
								  if(!changeYS(vcN,vc)){
									  Avvresult=false;
									  break;
								  }
							  }
							  if(!Avvresult){
								  break;
							  }
						  }
					  }
				  }
				  //停留期
				  if(amsdStatus==1){
						  Amsdresult=false;
				  }else{
					  if(AMSDlistN==null){
						  Amsdresult=false;
					  }else{
						  for(String vcN:AMSDlistN){
							  for(String vc:AMSDlist){
								  if(!changeYS(vcN,vc)){
									  Amsdresult=false;
									  break;
								  }
							  }
							  if(!Amsdresult){
								  break;
							  }
						  }
					  }
				  }
				  if(jtStatus==1){
						  jtresult=false;
				  }else{
					  if(JTlistN==null){
						  jtresult=false;
					  }else{
						  for(String vcN:JTlistN){
							  for(String vc:JTlist){
								  if(vcN.equals(vc)){
									  jtresult=false;
									  break;
								  }
							  }
							  if(!jtresult){
								  break;
							  }
						  }
					  }
				  }
				  if(pncStatus==1){
						  pncresult=false;
				  }else{
					  if(PNClistN==null){
						  pncresult=false;
					  }else{
						  for(String vcN:PNClistN){
							  for(String vc:PNClist){
								  if(vcN.equals(vc)){
									  pncresult=false;
									  break;
								  }
							  }
							  if(!pncresult){
								  break;
							  }
						  }
					  }
				  }
		  if(Vcresult==false&&Poresult==false&&Avtresult==false&&Avvresult==false&&Amsdresult==false&&jtresult==false&&pncresult==false){
			  result=false;
			  break;
		  }
	    }
	  }else{
			  result=true;
	  }
		  return result;
	  }
   	/**判断区间值
 	*@prama s第一部分区间，s1：第二部分区间
 	*@return Boolean 是否含有交集
	**/
	  public static boolean changeYS(String s,String s1){
		  boolean result=true;
		  String[] q= s.split("\\|");
		  String[] t=s1.split("\\|");
		  RangeC qc=changeList(q);
		  RangeC tc=changeList(t);
		  if(qc.getUnit().equals(tc.getUnit())){
		  int max=0;
		  if(qc.getRight().equals("-")){
			  if(tc.getRight().equals("-")){
				  if(Integer.valueOf(qc.getLeft())>Integer.valueOf(tc.getLeft())){
					  max=Integer.valueOf(qc.getLeft())+1;
				  }else{
					  max=Integer.valueOf(tc.getLeft())+1;
				  }
			  }else{
				  max=Integer.valueOf(tc.getRight())+1;
			  }
		  }else{
			  if(tc.getRight().equals("-")){
				 if(Integer.valueOf(qc.getRight())>Integer.valueOf(tc.getLeft())){
					 max=Integer.valueOf(qc.getRight())+1;
				 }else{
					 max=Integer.valueOf(tc.getLeft())+1;
				 }
			  }else{
				  if(Integer.valueOf(qc.getRight())>Integer.valueOf(tc.getRight())){
						 max=Integer.valueOf(qc.getRight())+1;
					 }else{
						 max=Integer.valueOf(tc.getRight())+1;
					 }
			  }
		  }
		  for(double i=0;i<max;i=i+0.5){
			 if(isElment(qc,i)&&isElment(tc,i)){
				 result=false;
				 break;
			 }
		  }
		  }
		  return  result;
	  }
	/*判处理区间值，例如 符号|数字
 	*prama q:将区间分离，分为数字跟符合，然后放入对应的对象中保存
 	*return	RangeC  对象（Unit（单位），left(左区间)，right（右区间），leftE（是否等于左区间），rightE（是否等于右区间））
	*/
	  public  static RangeC  changeList(String[] q){
		    RangeC c=new RangeC();
		    String unit="D";//保存单位没有默认为天
		    //大于>
			 if(q[0].equals(CheckListConstant.BT)){
				 String number=q[1].split(" ")[0];
				 //保存数据单位
				 if(q[1].split(" ").length>1){
					 unit= q[1].split(" ")[1];
				 }
				 c.setLeft(number);
				 c.setUnit(unit);
				 c.setRight("-");
                 		c.setLeftE(false);
                 		c.setRightE(false);
			 }
			 //小于<
			 if(q[0].equals(CheckListConstant.LT)){
				 String number=q[1].split(" ")[0];
				 if(q[1].split(" ").length>1){
					 unit= q[1].split(" ")[1];
				 }
				 c.setUnit(unit);
				 c.setRight(number);
				 c.setLeft("-");
				 c.setLeftE(false);
				 c.setRightE(false);
			 }
			 //等于=
			 if(q[0].equals(CheckListConstant.EQ)){
				 String number=q[1].split(" ")[0];
				 if(q[1].split(" ").length>1){
					 unit= q[1].split(" ")[1];
				 }
				 c.setUnit(unit);
				 c.setRight(number);
				 c.setLeft(number);
				 c.setLeftE(true);
				 c.setRightE(true);
				
			 }
			 //大于等于>=
			 if(q[0].equals(CheckListConstant.BTE)){
				 String number=q[1].split(" ")[0];
				 if(q[1].split(" ").length>1){
					 unit= q[1].split(" ")[1];
				 }
				 c.setUnit(unit);
				 c.setRight("-");
				 c.setLeft(number);
				 c.setLeftE(true);
				 c.setRightE(false);
				
			 }
			 //小于等于<=
			 if(q[0].equals(CheckListConstant.LTE)){
				 String number=q[1].split(" ")[0];
				 if(q[1].split(" ").length>1){
					 unit= q[1].split(" ")[1];
				 }
				 c.setUnit(unit);
				 c.setRight(number);
				 c.setLeft("-");
				 c.setLeftE(false);
				 c.setRightE(true);
			 }
			 //区间[]
			 if(q[0].equals(CheckListConstant.RANGE)){
				 String number1=q[1].split(" ")[0];
				 String number2=q[2].split(" ")[0];
				 if(q[1].split(" ").length>1){
					 unit= q[1].split(" ")[1];
				 }
				 c.setUnit(unit);
				 c.setRight(number2);
				 c.setLeft(number1);
				 c.setLeftE(false);
				 c.setRightE(false);
			 }
			 return c;
	  }
	/**判断数字是否存在区间
 	*prama c：区间，i：数字
 	*return 返回是否存在区间
 	**/
	  public static boolean isElment(RangeC c,double i){
		 Boolean leftE=c.isLeftE();
		 Boolean rightE=c.isRightE();
		 if(leftE==true&&rightE==true){
			 if(Integer.valueOf(c.getLeft())==i){
				 return true;
			 }else{
				 return false;
			 }
		 }else{
		 if(c.getRight().equals("-")){
			 if(leftE){
				if(Integer.valueOf(c.getLeft())>i){
					return false;
				}else{
					return true;
				}
			 }else{
				 if(Integer.valueOf(c.getLeft())>=i){
						return false;
					}else{
						return true;
					}
			 }
		 }else{
			 if(c.getLeft().equals("-")){
				 if(rightE){
					 if(Integer.valueOf(c.getRight())<i){
							return false;
						}else{
							return true;
						}
				 }else{
					 if(Integer.valueOf(c.getRight())<=i){
							return false;
						}else{
							return true;
						}
				 }
			 }else{
					if(Integer.valueOf(c.getLeft())>i){
							return false;
					}else if(Integer.valueOf(c.getRight())<i){
						return false;
					}else{
							return true;
					}
			 }
		 }
		 }
	  }

}
