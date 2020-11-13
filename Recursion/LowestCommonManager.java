import java.util.*;
class OrgChart{

	char name;
	List<OrgChart> directReports;

	OrgChart(char name){
		this.name = name;
		this.directReports = new ArrayList<OrgChart>();
	}

	public void addDirectReports(OrgChart[] directReports){
		for (OrgChart directReport : directReports){
			this.directReports.add(directReport);
		}
	}

}
class OrgInfo{

	OrgChart lowestCommonManager;
	int numImportantReports;

	OrgInfo(OrgChart lowestCommonManager, int numImportantReports){
		this.lowestCommonManager = lowestCommonManager;
		this.numImportantReports = numImportantReports;
	}
}
class Question{

	public static OrgChart getLowestCommonManager(OrgChart topManager, OrgChart reportOne, OrgChart reportTwo){
		return getOrgInfo(topManager,reportOne,reportTwo).lowestCommonManager;
	}

	public static OrgInfo getOrgInfo(OrgChart manager, OrgChart reportOne, OrgChart reportTwo){
		int numImportantReports = 0;

		//do dfs traversal
		for (OrgChart directReport : manager.directReports){
			OrgInfo orgInfo = getOrgInfo(directReport,reportOne,reportTwo);
			//this step is necessary to avoid unncessary traversal of other direct reports if u have found answer under one of the direct reports
			if (orgInfo.lowestCommonManager != null)return orgInfo;

			numImportantReports += orgInfo.numImportantReports;
		}

		if (manager == reportOne || manager == reportTwo) numImportantReports++;

		//unlike other code, here lowerCommonManager is populated only if we found 2 reports; with this we can avoid uncessary traversals
		OrgChart lowestCommonManager = numImportantReports == 2 ? manager : null;

		OrgInfo newOrgInfo = new OrgInfo(lowestCommonManager,numImportantReports);
		return newOrgInfo;
	}

	public static void main(String[] args){
		Map<Character,OrgChart> orgChartNodes = new HashMap<Character,OrgChart>();

		String alphabet = "ABCDEFGHI";
		for (char a : alphabet.toCharArray()){
			orgChartNodes.put(a,new OrgChart(a));
		}

		//A is ancestor for B,C
		orgChartNodes.get('A').addDirectReports(new OrgChart[]{orgChartNodes.get('B'),orgChartNodes.get('C')});
		//B is ancestor of D E
		orgChartNodes.get('B').addDirectReports(new OrgChart[]{orgChartNodes.get('D'),orgChartNodes.get('E')});
		//C is ancestor for F G
		orgChartNodes.get('C').addDirectReports(new OrgChart[]{orgChartNodes.get('F'),orgChartNodes.get('G')});
		//D is ancestor for H I
		orgChartNodes.get('D').addDirectReports(new OrgChart[]{orgChartNodes.get('H'),orgChartNodes.get('I')});

		OrgChart topManager = orgChartNodes.get('A');
		OrgChart reportOne = orgChartNodes.get('E');
		OrgChart reportTwo = orgChartNodes.get('I');

		OrgChart lcm = getLowestCommonManager(topManager,reportOne,reportTwo);
		System.out.println(lcm.name);
	}

}

//my solution:
import java.util.*;
class Program {
  public static OrgChart getLowestCommonManager(
      OrgChart topManager, OrgChart reportOne, OrgChart reportTwo) {
    return findLCA(topManager,reportOne,reportTwo).lca;
  }
	
	public static OrgInfo findLCA(OrgChart manager, OrgChart reportOne, OrgChart reportTwo){
		int reports = 0;
		
		for (OrgChart directReport : manager.directReports){
			OrgInfo orgInfo = findLCA(directReport,reportOne,reportTwo);
			if (orgInfo.lca != null) return orgInfo;
			reports += orgInfo.reports;
		}
		
		if (manager == reportOne || manager == reportTwo) reports++;
		
		OrgChart lca = reports == 2 ? manager : null;
		return new OrgInfo(lca,reports);
	}

  	static class OrgChart {
	    public char name;
	    public List<OrgChart> directReports;

	    OrgChart(char name) {
	      this.name = name;
	      this.directReports = new ArrayList<OrgChart>();
	    }

	    // This method is for testing only.
	    public void addDirectReports(OrgChart[] directReports) {
	      for (OrgChart directReport : directReports) {
	        this.directReports.add(directReport);
	      }
	    }
  	}
	
	static class OrgInfo{
		OrgChart lca;
		int reports;
	
		public OrgInfo(OrgChart lca, int reports){
			this.lca = lca;
			this.reports = reports;
		}
	}
	
}
