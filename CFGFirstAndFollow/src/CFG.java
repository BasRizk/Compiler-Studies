import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CFG {

	private String cfgString = "";
	private ArrayList<Character> cfgVarsInOrder;
	private HashMap<Character, ArrayList<String>> cfgDict;
	private HashMap<Character, ArrayList<Character>> firstDict; 
	private HashMap<Character, ArrayList<Character>> followDict;
	
	public CFG (String cfgString) {
		this.cfgString = cfgString;
		cfgVarsInOrder = new ArrayList<Character>();
		cfgDict = new HashMap<>();
		firstDict = new HashMap<>();
		followDict = new HashMap<>();
		dictionarize();
	}
	
	public void dictionarize() {
		String [] sentences = cfgString.split(";");
		for(String s : sentences) {
			String [] elements = s.split(",");
			Character key = elements[0].charAt(0);
			cfgVarsInOrder.add(key);
			ArrayList<String> value = new ArrayList<>();
			for(int i = 1; i < elements.length; i++) {
				value.add(elements[i]);
			}
			cfgDict.put(key, value);
		}
//		System.out.println("CFG DICT: " + cfgDict);
	}
	
	public String stringfy(HashMap<Character, ArrayList<Character>> dict) {
		String outString = "";
		
		for(Character key : cfgVarsInOrder) {
			outString += key + ",";
			Collections.sort(dict.get(key));
			boolean add$ = false; 
			for(Character c : dict.get(key)) {
				if (c == '$') {
					add$ = true;
					continue;
				}
				outString += c;
			}
			
			if (add$) {
				outString += '$';
			}
			
			outString += ";";
		}
//		for (Map.Entry<Character,ArrayList<Character>> entry : dict.entrySet()) {
//			
//		}
		
		return outString.substring(0, outString.length() -1);
	}
	
	
	public String First() {
		
		for (Character var : cfgDict.keySet())  
            firstDict.put(var, new ArrayList<Character>());
		
		boolean change = true;
		while(change) {
			change = false;

//			for (Map.Entry<Character,ArrayList<String>> entry : cfgDict.entrySet()) {
			for(Character key : cfgVarsInOrder) {
				ArrayList<String> terms = cfgDict.get(key);
				for(String term : terms) {
					if (doesAllContainEps(term)) {
//						ArrayList<Character> firsts = getFirst(entry.getKey());
						if(!firstDict.get(key).contains('e')) {
							firstDict.get(key).add('e');
							change = true;
						}
					}
					for(int i = 0; i < term.length(); i++) {
						boolean addup = false;
						if (i == 0) {
							addup = true;
						} else if(doesAllContainEps(term, 0, i)) {
							addup = true;
						}
						
						if(addup) {
							ArrayList<Character> outsideIntersection = 
									getOutsideRightIntersection(
											getFirst(key),
											getFirst(term.charAt(i)));
							if(outsideIntersection.size() > 0) {
								firstDict.get(key).addAll(outsideIntersection);
								change = true;
							}
						}	
					}
				}
			}	
		}
		
//		System.out.println(firstDict.toString());
		
		return stringfy(firstDict);
	}
	
	private ArrayList<Character> getFirst(Character var) {
		if(!Character.isUpperCase(var)) {
			ArrayList<Character> firsts = new ArrayList<>();
			firsts.add(var);
			return firsts;
		}
		return firstDict.get(var);
	}
	
	private ArrayList<Character> getFirst(String term) {
		ArrayList<Character> firsts = new ArrayList<>();
		for(int i=0; i < term.length(); i++) {
			firsts = getOutsideRightIntersection(getFirst(term.charAt(i)), firsts);
			firsts.addAll(getFirst(term.charAt(i)));
			if (!firsts.contains('e')) {
				break;
			}
		}
		return firsts;
	}
	
	private ArrayList<Character> getOutsideRightIntersection(
			ArrayList<Character> firsts0, ArrayList<Character> firstsI) {
		
		ArrayList<Character> outsideIntersection = new ArrayList<Character>();
		
		for(Character someFirst : firstsI) {
			if(!firsts0.contains(someFirst)) {
				if(someFirst != 'e') {
					outsideIntersection.add(someFirst);
				}
			}
		}
		return outsideIntersection;
	}
	
	
	private boolean doesFirstContainEps(Character var) {
		ArrayList<Character> firsts = getFirst(var);
		if (firsts.contains('e')) {
			return true;
		}
		return false;
	}
	
	private boolean doesFirstContainEps(String term) {
		ArrayList<Character> firsts = getFirst(term);
		if (firsts.contains('e')) {
			return true;
		}
		return false;
	}
	
	private boolean doesAllContainEps(String term) {
		return doesAllContainEps(term, 0, term.length());
	}
	
	private boolean doesAllContainEps(String term, int s, int f) {
		// f is excluded	
		for(int i=s; i < f; i++) {
			if (!doesFirstContainEps(term.charAt(i))) {
				return false;
			}
		}
		return true;	
	}
	
	private ArrayList<Character> getFollow(Character var) {
		if(!Character.isUpperCase(var)) {
			ArrayList<Character> empty = new ArrayList<>();
			return empty;
		}
		return followDict.get(var);
	}
	
	public String Follow() {
		for (Character var : cfgDict.keySet())  
            followDict.put(var, new ArrayList<Character>());
		followDict.get(cfgVarsInOrder.get(0)).add('$');
//		System.out.println("followDict = " + followDict);
		ArrayList<Character> outsideIntersection;
		boolean change = true;
		while(change) {
			change = false;
//			for (Map.Entry<Character,ArrayList<String>> entry : cfgDict.entrySet()) {
			for(Character key : cfgVarsInOrder) {
				ArrayList<String> terms = cfgDict.get(key);
//				System.out.println("KEY = " + entry.getKey());
				for(String term : terms) {
					char A, B = 'e';
					String beta;
					A = key;
					ArrayList<Character> Bs = new ArrayList<>();
					ArrayList<String> betas = new ArrayList<>();
//					System.out.println("TERM = " + term);
					for(int i= 0; i < term.length(); i++) {
						B = term.charAt(i);
						
						if(i == term.length() - 1)
							beta = "e";
						else 
							beta = term.substring(i+1, term.length());
						
						if(!Character.isUpperCase(B)) {
							continue;
						}
						Bs.add(B);
						betas.add(beta);
//						System.out.println("----B: " + B + ", beta: " + beta);

					}	
					
					
//					if(term.length() == 1) {
//						B = term.charAt(0);
//						beta = 'e';
//						if(Character.isUpperCase(B)) {
//							Bs.add(B);
//							betas.add(beta);
//						}			
//					}
					
					
//					if (Bs.size() > 0)
//						System.out.println("Bs: " + Bs.toString());
					for(int i= 0; i < Bs.size(); i++) {
						B = Bs.get(i);
						beta = betas.get(i);
				
//						System.out.println(i + ". B: " + B + ", beta: " + beta + ", A: " + A);
						outsideIntersection = getOutsideRightIntersection(
										getFollow(B), getFirst(beta));
						if(outsideIntersection.size() > 0) {
//							System.out.println("followDict = " + followDict);
//							System.out.println("outsideIntersection: " + outsideIntersection.toString());
							followDict.get(B).addAll(outsideIntersection);
							change = true;
//							System.out.println("CASE 1");
//							System.out.println("...ADDING " +
//									outsideIntersection.toString() +
//									" to " + B);
						}
						
						if(doesFirstContainEps(beta)) {
//							System.out.println(i + ". B: " + B + ", beta: " + beta + ", A: " + A);
							outsideIntersection = getOutsideRightIntersection(
											getFollow(B), getFollow(A));
							if(outsideIntersection.size() > 0) {
//								System.out.println("followDict = " + followDict);
//								System.out.println("outsideIntersection: " + outsideIntersection.toString());
								followDict.get(B).addAll(outsideIntersection);
								change = true;
//								System.out.println("CASE 2");
//								System.out.println("...ADDING " +
//										outsideIntersection.toString() +
//										" to " + B);
							}
						}						
					}
				}
			}	
		}
		
//		System.out.println(followDict.toString());

		return stringfy(followDict);
	}
	
	private static void runTests() {
		ArrayList<String> testCases = new ArrayList<>(
				Arrays.asList(
						"S,ScT,T;T,aSb,iaLb,e;L,SdL,S",
						"S,ABCDZ;A,a,e;B,b,e;C,c;D,d,e;Z,z,e",
						"Z,TX;X,+TX,e;T,FV;V,*FV,e;F,(Z),i",
						"S,ACB,CbB,Ba;A,da,BC;B,g,e;C,h,e",
						"S,Bb,Cd;B,aB,e;C,cC,e",
						"S,aBDh;B,cC;C,bC,e;D,EF;E,g,e;F,f,e",
						"S,A;A,aBF;F,dF,e;B,b;C,g",
						"S,(L),a;L,SQ;Q,_SQ,e",
						"S,AaAb,BbBa;A,e;B,e",
						"E,TQ;Q,+TQ,e;T,FW;W,xFW,e;F,(E),i",
						"S,ACB,CbB,Ba;A,da,BC;B,g,e;C,h,e",
						"S,aTbS,e;T,aTb,e",
						"S,SAB,SBC,e;A,aAa,e;B,bB,e;C,cC,e",
						"S,AB;A,aA,b;B,CA;C,cC,d",
						"S,lAr,a;A,lArB,aB;B,cSB,e",
						"S,aA;A,SB,e;B,bA,cA"));
		ArrayList<String> firsts = new ArrayList<>(
				Arrays.asList(
						"S,acei;T,aei;L,acdei",
						"S,abc;A,ae;B,be;C,c;D,de;Z,ez",
						"Z,(i;X,+e;T,(i;V,*e;F,(i",
						"S,abdegh;A,degh;B,eg;C,eh",
						"S,abcd;B,ae;C,ce",
						"S,a;B,c;C,be;D,efg;E,eg;F,ef",
						"S,a;A,a;F,de;B,b;C,g",
						"S,(a;L,(a;Q,_e",
						"S,ab;A,e;B,e",
						"E,(i;Q,+e;T,(i;W,ex;F,(i",
						"S,abdegh;A,degh;B,eg;C,eh",
						"S,ae;T,ae",
						"S,abce;A,ae;B,be;C,ce",
						"S,ab;A,ab;B,cd;C,cd",
						"S,al;A,al;B,ce",
						"S,a;A,ae;B,bc"));
		ArrayList<String> follows = new ArrayList<>(
				Arrays.asList(
						"S,bcd$;T,bcd$;L,b",
						"S,$;A,bc;B,c;C,dz$;D,z$;Z,$",
						"Z,)$;X,)$;T,)+$;V,)+$;F,)*+$",
						"S,$;A,gh$;B,agh$;C,bgh$",
						"S,$;B,b;C,d",
						"S,$;B,fgh;C,fgh;D,h;E,fh;F,h",
						"S,$;A,$;F,$;B,d$;C,",
						"S,)_$;L,);Q,)",
						"S,$;A,ab;B,ab",
						"E,)$;Q,)$;T,)+$;W,)+$;F,)+x$",
						"S,$;A,gh$;B,agh$;C,bgh$",
						"S,$;T,b",
						"S,abc$;A,abc$;B,abc$;C,abc$",
						"S,$;A,cd$;B,$;C,ab",
						"S,cr$;A,r;B,r",
						"S,bc$;A,bc$;B,bc$"));
		int totalIncorrectFirsts = 0;
		int totalIncorrectFollows = 0;
		for(int i= 0; i < testCases.size(); i++) {
			CFG cfg = new CFG(testCases.get(i));
			String firstEncoding = cfg.First();
			String followEncoding = cfg.Follow();
			System.out.println("First: " + firstEncoding);
			if (!firstEncoding.equals(firsts.get(i))) {
				System.out.println("INCORRECT");
				System.out.println("-----ORIGINAL:" + firsts.get(i));
				System.out.println("-----NON-ORIG:" + firstEncoding);
				totalIncorrectFirsts++;
			} else {
				System.out.println("PASS");
			}
			System.out.println("Follow: " + followEncoding);
			if (!followEncoding.equals(follows.get(i))) {
				System.out.println("INCORRECT");
				System.out.println("-----ORIGINAL:" + follows.get(i));
				System.out.println("-----NON-ORIG:" + followEncoding);
				totalIncorrectFollows += 1;
			} else {
				System.out.println("PASS");
			}
			System.out.println();
		}
		
		float correctPercentage =
				((testCases.size()*2.0f - (totalIncorrectFirsts + totalIncorrectFollows))
				/(testCases.size()*2.0f))*100;
		System.out.println("\n====================================\n" +
						    "   		    Summary\n" +
						    "              Firsts | Follows\n" +
							"> Incorrects:   " + totalIncorrectFirsts +
							"	 " + totalIncorrectFollows + "\n" +
							"-------------------------------------\n" +
							"> " + correctPercentage + "% of " +
							(testCases.size()*2) + " passes were correct");
	}
	
	
	/**
	 * CFG: S,ScT,T;T,aSb,iaLb,e;L,SdL,S
	 * 
	 * Firsts: S,acei;T,aei;L,acdei
	 * 
	 * Follows: S,bcd$;T,bcd$;L,b
	 * 
	 */
	public static void main(String[] args) {
		String input = "S,ScT,T;T,aSb,iaLb,e;L,SdL,S";
		CFG cfg = new CFG(input);
		String firstEncoding = cfg.First();
		String followEncoding = cfg.Follow();
		System.out.println("First: " + firstEncoding);
		System.out.println("Follow: " + followEncoding);
//		runTests();
	}
}
