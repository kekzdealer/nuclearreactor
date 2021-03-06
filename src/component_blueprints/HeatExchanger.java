package component_blueprints;

import java.util.HashMap;
import java.util.Map.Entry;

public class HeatExchanger extends HeatManagementComponent {
	
	private final double COMPONENT_EXCHANGE_RATE;
	private final double HULL_EXCHANGE_RATE;
		
	public HeatExchanger(int posX, int posY, 
			double heatCapacity, 
			double componentExchangeRate, double hullExchangeRate) {
		
		super(ComponentType.HeatExchanger, posX, posY, heatCapacity);
		
		this.COMPONENT_EXCHANGE_RATE = componentExchangeRate;
		this.HULL_EXCHANGE_RATE = hullExchangeRate;
	}
	
	public HeatExchanger(int posX, int posY, 
			Double[] data) {
		
		super(ComponentType.HeatExchanger, posX, posY, data[0]);
		
		this.COMPONENT_EXCHANGE_RATE = data[1];
		this.HULL_EXCHANGE_RATE = data[2];
	}
	
	public double getCOMPONENT_EXCHANGE_RATEe() {
		return COMPONENT_EXCHANGE_RATE;
	}
	
	public double getHULL_EXCHANGE_RATE() {
		return HULL_EXCHANGE_RATE;
	}
	
	/**
	 * Calculates how much heat can be taken from each participant while not exceeding 
	 * the maximum throughput of this heat exchanger.
	 * Assigned throughput is directly relative to how large the fraction of the total heat
	 * between all participants is.
	 * @param participants : The heat exchanger itself and up to four surrounding components
	 * @return A map with each participant and the throughput it got assigned
	 */
	public HashMap<HeatManagementComponent, Double> calculateFractionsOfThrougput(HashMap<HeatManagementComponent, Double> participants) {
		double summedHeat = 0;
		for(Double i : participants.values()) {
			summedHeat += i;
		}
		for(Entry<HeatManagementComponent, Double> i : participants.entrySet()) {
			final double fractionOfTotalHeat = i.getValue() / summedHeat;
			final double fractionOfThroughput = fractionOfTotalHeat * COMPONENT_EXCHANGE_RATE;
			participants.put(i.getKey(), fractionOfThroughput);
		}		
		return participants;
	}
	
}
