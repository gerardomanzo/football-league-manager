package flm.campionati;

import java.util.Comparator;

import flm.squadre.Squadra;

public class SquadreComparator implements Comparator<Squadra>{
	@Override
	public int compare(Squadra s1, Squadra s2) {
		int vittorie1 = s1.getVittorie();
		int pareggi1 = s1.getPareggi();
		int goalFatti1 = s1.getGoalFatti();
		int goalSubiti1 = s1.getGoalSubiti();

		int vittorie2 = s2.getVittorie();
		int pareggi2 = s2.getPareggi();
		int goalFatti2 = s2.getGoalFatti();
		int goalSubiti2 = s2.getGoalSubiti();

		if(3*vittorie1 + pareggi1 > 3*vittorie2 + pareggi2)
			return -1;
		else if(3*vittorie1 + pareggi1 < 3*vittorie2 + pareggi2)
			return 1;
		else {
			if(goalFatti1 > goalFatti2)
				return -1;
			else if(goalFatti1 < goalFatti2)
				return 1;
			else {
				if(goalSubiti1 < goalSubiti2)
					return -1;
				else if(goalSubiti1 > goalSubiti2)
					return 1;
				else
					return s1.getNomeSquadra().compareTo(s2.getNomeSquadra());
			}
		}
	}
}
