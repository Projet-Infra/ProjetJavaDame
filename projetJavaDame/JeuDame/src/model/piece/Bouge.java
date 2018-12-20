package model.piece;

public interface Bouge {

		public void bougeInterne(String orientation, boolean depuisServeur, int nombreCase);
		public void bouge(String orientation, boolean depuisServeur, int nombreCase);
		
		public void testSiTransformation();
}
