public class HiddenObject extends Observation {

	private InventoryItem conceal;
	private static int observationIndex = Game.rand
			.nextInt(Strings.observation2.length);

	public HiddenObject(InventoryItem conceal) {
		super(Strings.observation[observationIndex]);
		this.conceal = conceal;
		init();
	}

	private void init() {
		actions.add(new InspectAction(Strings.observation1[Game.rand
				.nextInt(Strings.observation1.length)]
				+ conceal.displayName
				+ " " + Strings.observation2[observationIndex],Strings.observation[observationIndex], conceal));
	}

}
