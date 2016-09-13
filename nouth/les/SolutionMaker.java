package nouth.les;
import java.util.*;

public class SolutionMaker{

	

public Solution solve(State startState, String variable){
	Solution solution = new Solution();

	solution.startingState = startState;
	solution.variable = new VarEx(variable);

	StateSearcher searchie = new StateSearcher(startState);
	solution.foundState = searchie.find(variable);

	Integer rank = Polynom.rankOfEq(solution.foundState.stateEx, variable);
	System.out.println("Solutionmaker rank = " + rank);
	
	if(rank==null){
		rank = -1;
		}

	switch(rank){
		case 2:
		solution.allValuesStates = solveQuadratic(solution.foundState, variable);
		break;
		case -1:
		default:
		ArrayList<State> foundSolutions = new ArrayList<State>();
		ComputeSearcher cs = new ComputeSearcher();
		solution.foundState = cs.search(solution.foundState);
		solution.allValuesStates.add(solution.foundState);
		}
	
	for(State st : solution.allValuesStates){
		System.out.println("all values : " + st.stateEx.reportForTex());
		Ex first = st.stateEx.getSubEx(0);
		if((first instanceof VarEx)&&(((VarEx)first).unit.equals(variable))){
			first = st.stateEx.getSubEx(1);
			}
		solution.allValues.add(first.copy());
		}

	//TODO handle conditions
	solution.legitValuesStates = solution.allValuesStates;
	solution.legitValues = solution.allValues;

		
	return solution;
		}
	

private ArrayList<State> solveQuadratic(State state, String var){
	Ex ex = state.stateEx;

	if(ex.getSubEx(0) instanceof AddEx){
		ex = ex.getSubEx(0);
		}
	else{
		ex = ex.getSubEx(1);
		}

	Ex a = new PlainEx(0);
	Ex b = new PlainEx(0);
	Ex c = new PlainEx(0);


	for(Ex sub : ex.getSubExList()){
		if(!(sub.varContains(var))){
			c = c.add(sub.copy());
			}
		else{
			switch(((PlainEx)Polynom.exponentOfMember(sub,var)).value){
				case 2:
					a = a.add(Polynom.constantOfMember(sub, var).copy());
					break;
				case 1:
					b = b.add(Polynom.constantOfMember(sub, var).copy());
					break;
				}
			}
		}	
	
	ArrayList<Ex> abc = new ArrayList<Ex>();
	abc.add(a);
	abc.add(b);
	abc.add(c);
	ArrayList<Ex> postAbc = new ArrayList<Ex>();
	System.out.println("solveQuadratic ready for abc");
	
	ComputeSearcher cs = new ComputeSearcher();
	for(Ex joe : abc){
		State ns = new State(joe);
		ns = cs.search(ns);
		postAbc.add(ns.stateEx);
		}

	System.out.println("solveQuadratic abc : " + a.report() + "_" + b.report() + "_" + c.report());

	a = postAbc.get(0);
	b = postAbc.get(1);
	c = postAbc.get(2);

	System.out.println("solveQuadratic abc : " + a.report() + "_" + b.report() + "_" + c.report());

	Ex underSqRt = new AddEx();
	underSqRt.add(b.copy().toPower(new PlainEx(2)));
	underSqRt.add(a.copy().multi(c.copy()).multi(new PlainEx(-4)));

	Ex sqRt = underSqRt.toPower((new PlainEx(1)).div(new PlainEx(2)));
	
	ArrayList<State> branches = new ArrayList<State>();
	
	System.out.println("sq before looú");
	int i;
	for(i=0;i<2;i++){
		Ex add = (((new PlainEx(-1)).multi(b.copy())).add(sqRt.copy()));
		Ex div = add.div((new PlainEx(2)).multi(a.copy()));
		branches.add(new State(new EqEx(div,new VarEx(var))));
		sqRt = sqRt.multi(new PlainEx(-1));
		System.out.println("SolveQuadratic adding " + div.reportForTex());
		}
	
	ArrayList<State> founds = new ArrayList<State>();

	for(State br : branches){
		state.children.add(br); //add to children of the original state		
		founds.add(cs.search(br));
		}	
	

	return founds;

	}

}
