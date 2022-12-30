import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class T10_3714415_BASEM_RIZK {

	// T10_3714415_BASEM_RIZK

	static class CFG {

		/**
		 * Creates an instance of the CFG class. This should parse a string
		 * representation of the grammar and set your internal CFG attributes
		 * 
		 * @param grammar A string representation of a CFG
		 */
		private String grammar;
		private ArrayList<Character> cfgVarsInOrder;
		private HashMap<Character, ArrayList<String>> cfgDict;
		private HashMap<Character, ArrayList<Character>> firstDict; 
		private HashMap<Character, ArrayList<Character>> followDict;	
		private HashMap<Character, HashMap<Character, String>> predictiveParsingTable;
		
		private static final Character EPSILON = 'e';
		private static final Character DOLLAR_SIGN = '$';
		private static final String ERROR = "ERROR";
		
		
		public CFG (String grammar) {
			this.grammar = grammar;
			dictionarize();
		}
		
		public void dictionarize() {
			cfgVarsInOrder = new ArrayList<Character>();
			cfgDict = new HashMap<>();
			String [] sentences = grammar.split(";");
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
		}
		
		private String stringfyCharArrayList(HashMap<Character, ArrayList<Character>> dict) {
			String outString = "";
			
			for(Character key : cfgVarsInOrder) {
				outString += key + ",";
				Collections.sort(dict.get(key));
				boolean add$ = false; 
				for(Character c : dict.get(key)) {
					if (c == DOLLAR_SIGN) {
						add$ = true;
						continue;
					}
					outString += c;
				}
				
				if (add$) {
					outString += DOLLAR_SIGN;
				}
				
				outString += ";";
			}
			return outString.substring(0, outString.length() -1);
		}
		
		private String stringfyDHashMap(HashMap<Character, HashMap<Character, String>> dict) {
			String output = "";
			for(Character key: cfgVarsInOrder) {
				HashMap<Character, String> elementRow = dict.get(key); 
				if(elementRow == null)
					continue;
				for(Map.Entry<Character,String> entry : elementRow.entrySet()) {
					output += key + "," + entry.getKey() + "," + entry.getValue() + ";";
				}
			}
			return output.substring(0, output.length()-1);
		}
		
		public String First() {
			firstDict = new HashMap<>();

			for (Character var : cfgDict.keySet())  
	            firstDict.put(var, new ArrayList<Character>());
			
			boolean change = true;
			while(change) {
				change = false;

				for(Character key : cfgVarsInOrder) {
					ArrayList<String> terms = cfgDict.get(key);
					for(String term : terms) {
						if (doesAllContainEps(term)) {
							if(!firstDict.get(key).contains(EPSILON)) {
								firstDict.get(key).add(EPSILON);
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
			return stringfyCharArrayList(firstDict);
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
				if (!firsts.contains(EPSILON)) {
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
					if(someFirst != EPSILON) {
						outsideIntersection.add(someFirst);
					}
				}
			}
			return outsideIntersection;
		}
		
		
		private boolean doesFirstContainEps(Character var) {
			ArrayList<Character> firsts = getFirst(var);
			if (firsts.contains(EPSILON)) {
				return true;
			}
			return false;
		}
		
		private boolean doesFirstContainEps(String term) {
			ArrayList<Character> firsts = getFirst(term);
			if (firsts.contains(EPSILON)) {
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
			followDict = new HashMap<>();

			for (Character var : cfgDict.keySet())  
	            followDict.put(var, new ArrayList<Character>());
			followDict.get(cfgVarsInOrder.get(0)).add(DOLLAR_SIGN);
			ArrayList<Character> outsideIntersection;
			boolean change = true;
			while(change) {
				change = false;
				for(Character key : cfgVarsInOrder) {
					ArrayList<String> terms = cfgDict.get(key);
					for(String term : terms) {
						char A, B = EPSILON;
						String beta;
						A = key;
						ArrayList<Character> Bs = new ArrayList<>();
						ArrayList<String> betas = new ArrayList<>();
						for(int i= 0; i < term.length(); i++) {
							B = term.charAt(i);
							
							if(i == term.length() - 1)
								beta = EPSILON.toString();
							else 
								beta = term.substring(i+1, term.length());
							
							if(!Character.isUpperCase(B)) {
								continue;
							}
							Bs.add(B);
							betas.add(beta);
						}	

						for(int i= 0; i < Bs.size(); i++) {
							B = Bs.get(i);
							beta = betas.get(i);
					
							outsideIntersection = getOutsideRightIntersection(
											getFollow(B), getFirst(beta));
							if(outsideIntersection.size() > 0) {
								followDict.get(B).addAll(outsideIntersection);
								change = true;
							}
							
							if(doesFirstContainEps(beta)) {
								outsideIntersection = getOutsideRightIntersection(
												getFollow(B), getFollow(A));
								if(outsideIntersection.size() > 0) {
									followDict.get(B).addAll(outsideIntersection);
									change = true;
								}
							}						
						}
					}
				}	
			}
			return stringfyCharArrayList(followDict);
		}
		
		

		/**
		 * Generates the parsing table for this context free grammar. This should set
		 * your internal parsing table attributes
		 * 
		 * @return A string representation of the parsing table
		 */
		public String table() {
			predictiveParsingTable = new HashMap<Character, HashMap<Character, String>>();
			First();
			Follow();
			
			for(Character key : cfgVarsInOrder) {
				predictiveParsingTable.put(key, new HashMap<Character, String>());
				ArrayList<String> terms = cfgDict.get(key);
				for(String term : terms) {
					ArrayList<Character> firsts = getFirst(term);
					for(Character first : firsts) {
						if(first.equals(EPSILON))
							continue;
						predictiveParsingTable.get(key).put(first, term);
					}
					if(firsts.contains(EPSILON)) {
						ArrayList<Character> follows = getFollow(key);
						for(Character follow : follows) {
							predictiveParsingTable.get(key).put(follow, term);
						}
					}
				}
			}
			
			return stringfyDHashMap(predictiveParsingTable);
		}
		

		/**
		 * Parses the input string using the parsing table
		 * 
		 * @param s The string to parse using the parsing table
		 * @return A string representation of a left most derivation
		 */
		public String parse(String s) {
			
			s+= DOLLAR_SIGN;
			String leftDerivation = "";			
			LinkedList<Character> pdaStack = new LinkedList<Character>();
			
			pdaStack.push(DOLLAR_SIGN);
			pdaStack.push(cfgVarsInOrder.get(0));
			leftDerivation += pdaStack.peek() + ",";
			boolean error = false;
			
			for(int input_i = 0; input_i < s.length(); input_i++) {
				
				while(pdaStack.peek() != s.charAt(input_i)) {
					
//					System.out.println("Current input " + " (" + input_i +")");
					Character onTape = s.charAt(input_i);
					Character onStack = pdaStack.peek();
//					System.out.println("OnStack = " + onStack + ", OnTape = " + onTape);

					if(onTape.equals(onStack)) {
						break;
					}
					
					if(!Character.isUpperCase(onStack)) {
						error = true; break;
					}
					
					HashMap<Character, String> varRow = predictiveParsingTable.get(onStack);
					if (varRow == null) {
						error = true; break;
					}
					
					String predictiveChoice = varRow.get(onTape);
					if(predictiveChoice == null) {
						error = true; break;
					}
					
//					System.out.println("About to push on stack: " + predictiveChoice);
					pdaStack.pop();
					for(int sub_i = predictiveChoice.length() - 1; sub_i >= 0; sub_i--) {
						Character toPushChar = predictiveChoice.charAt(sub_i);
						if (toPushChar.equals(EPSILON)) {
							continue;
						}
						pdaStack.push(predictiveChoice.charAt(sub_i));
					}
					
//					System.out.println("Stack after pushing: " + pdaStack.toString());
//					System.out.println("Peeking on that stack: " + pdaStack.peek());
					
					// Adding LEFT-DERIVATION per step
					leftDerivation += getCurrentLeftDerivation(s, input_i, pdaStack);
					leftDerivation += ",";
//					System.out.println("Current LeftDerivation: " + leftDerivation);
				}
				if(error) {
					 break;
				}
				pdaStack.pop();
			}
			
			if(error || pdaStack.size() > 0) {
				leftDerivation += ERROR;
			} else {
				leftDerivation = leftDerivation.substring(0, leftDerivation.length() - 1);
			}
			
			return leftDerivation;
		}
	}
	
	private static String getCurrentLeftDerivation(String s, int input_i, LinkedList<Character> pdaStack) {
		String newLeftDerivation = "";
		for(int i = 0; i < input_i; i++)
			newLeftDerivation += s.charAt(i);
		for(int i = 0; i < pdaStack.size() -1; i++) {
			newLeftDerivation += pdaStack.get(i);
		}
		return newLeftDerivation;
	}

	public static void main(String[] args) {

		String grammar = "S,iST,e;T,cS,a";
		String input1 = "iiac";
		String input2 = "iia";
		CFG g = new CFG(grammar);
		System.out.println(g.table());
		System.out.println(g.parse(input1));
		System.out.println(g.parse(input2));
		
//		runTests();
	}
	
	private static void runTests() {
		ArrayList<String> grammars = new ArrayList<>(
				Arrays.asList(
						"S,iST,e;T,cS,a",
						"S,TA;A,pTA,e;T,FB;B,mFB,e;F,lSr,i",
						"S,zToS,e;T,zTo,e",
						"S,AB;A,iA,n;B,CA;C,zC,o",
						"S,lLr,a;L,lLrD,aD;D,cSD,e",
						"S,aA;A,SB,e;B,pA,mA"));
		
		ArrayList<String> inputs1 = new ArrayList<>(
				Arrays.asList(
						"iiac",
						"imlipir",
						"zzoozo",
						"inzon",
						"laclacarr",
						"aamaamp"));
		
		ArrayList<String> inputs2 = new ArrayList<>(
				Arrays.asList(
						"iia",
						"imlipirl",
						"zoz",
						"nzin",
						"laclacarl",
						"aapaap"));
		
		ArrayList<String> answers1 = new ArrayList<>(
				Arrays.asList(
						"S,iST,iiSTT,iiTT,iiaT,iiacS,iiac",
						"S,TA,FBA,iBA,imFBA,imlSrBA,imlTArBA,imlFBArBA,imliBArBA,imliArBA,imlipTArBA,imlipFBArBA,imlipiBArBA,imlipiArBA,imlipirBA,imlipirA,imlipir",
						"S,zToS,zzTooS,zzooS,zzoozToS,zzoozoS,zzoozo",
						"S,AB,iAB,inB,inCA,inzCA,inzoA,inzon",
						"S,lLr,laDr,lacSDr,laclLrDr,laclaDrDr,laclacSDrDr,laclacaDrDr,laclacarDr,laclacarr",
						"S,aA,aSB,aaAB,aaB,aamA,aamSB,aamaAB,aamaSBB,aamaaABB,aamaaBB,aamaamAB,aamaamB,aamaampA,aamaamp"));
		
		ArrayList<String> answers2 = new ArrayList<>(
				Arrays.asList(
						"S,iST,iiSTT,iiTT,iiaT,ERROR",
						"S,TA,FBA,iBA,imFBA,imlSrBA,imlTArBA,imlFBArBA,imliBArBA,imliArBA,imlipTArBA,imlipFBArBA,imlipiBArBA,imlipiArBA,imlipirBA,ERROR",
						"S,zToS,zoS,zozToS,ERROR",
						"S,AB,nB,nCA,nzCA,ERROR",
						"S,lLr,laDr,lacSDr,laclLrDr,laclaDrDr,laclacSDrDr,laclacaDrDr,laclacarDr,ERROR",
						"S,aA,aSB,aaAB,aaB,aapA,aapSB,aapaAB,aapaSBB,aapaaABB,aapaaBB,aapaapAB,aapaapB,ERROR"));
		
		for(int i = 0; i < grammars.size(); i++) {
			System.out.println("=============================");
			System.out.println("------------ " + i + " ------------");
			String grammar = grammars.get(i);
			String input1 = inputs1.get(i);
			String input2 = inputs2.get(i);
			String answer1 = answers1.get(i);
			String answer2 = answers2.get(i);
			CFG g = new CFG(grammar);
			System.out.println(g.table());
			String deriv1 = g.parse(input1);
			System.out.println(deriv1);
			System.out.println(isCommaSeperatedStringsEqual(deriv1, answer1));
			String deriv2 = g.parse(input2);
			System.out.println(deriv2);
			System.out.println(isCommaSeperatedStringsEqual(deriv2, answer2));
		}		
	}
	
	private static boolean isCommaSeperatedStringsEqual(String s1, String s2) {
		String [] s1_array = s1.split(",");
		String [] s2_array = s2.split(",");
		if(s1_array.length != s2_array.length) {
			return false;
		}
		for(int i = 0; i < s1_array.length; i++) {
			String term = s1_array[0];
			boolean matched = false;
			for(int j = 0; j < s2_array.length; j++) {
				if(term.equals(s2_array[j])) {
					matched = true;
				}
			}
			if(!matched) {
				return false;
			}
		}
		return true;
	}

}
