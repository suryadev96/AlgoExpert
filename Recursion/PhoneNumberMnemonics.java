/*
phoneNumber = "1905"

[ "1w0j", "1w0k", ...]
*/
class Question{

	public static Map<Character,String> map = new HashMap<>();

	static {
		map.put('0',"0");
		map.put('1',"1");
		map.put('2',"abc");
		map.put('3',"def");
		map.put('4',"ghi");
		map.put('5',"jkl");
		map.put('6',"mno");
		map.put('7',"pqrs");
		map.put('8',"tuv");
		map.put('9',"wxyz");
	}

	public List<String> phoneNumberMnemonics(String phoneNumber){
		List<String> result = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		phoneNumberMnemonicsHelper(result, sb, phoneNumber, 0);
		return result;
	}

	public void phoneNumberMnemonicsHelper(List<String> result, StringBuilder sb, String phoneNumber, int index){
		if (index == phoneNumber.length()){
			result.add(sb.toString());
			return;
		}

		char digit = phoneNumber.charAt(index);
		for (char ch : map.get(digit).toCharArray()) {
			sb.append(ch);
			phoneNumberMnemonicsHelper(result, sb, phoneNumber, index + 1);
			sb.deleteCharAt(sb.length()-1);
		}
	}

}