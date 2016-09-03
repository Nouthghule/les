package nouth.les;

public class SolutionMaker{

public Solution solve(State startState, String variable){
	Solution solution = new Solution();

	solution.startingState = startState;
	solution.variable = new VarEx(variable);

	StateSearcher searchie = new StateSearcher(startState);
	solution.foundState = searchie.find(variable);

	
	return solution;
	}


}
