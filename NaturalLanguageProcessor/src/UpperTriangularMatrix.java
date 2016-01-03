
public class UpperTriangularMatrix {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int r = 4, c = 4;
		String[][] utm = new String[r][c];
		
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				if(i<=j)
					utm[i][j] = i+""+j;
				else
					utm[i][j] = "";
			}
		}

		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				System.out.print(utm[i][j]+"\t");
			}
			System.out.println();
		}
		
		/*for(int i = 0; i < 5; i++)
			System.out.print(utm[i][i]+" ");
		
		System.out.println();
		for(int i = 0; i < 5; i++)
			if(i+1 < 5)
				System.out.print(utm[i][i+1]+" ");
		
		System.out.println();
		for(int i = 0; i < 5; i++)
			if(i+2 < 5)
				System.out.print(utm[i][i+2]+" ");
		
		System.out.println();
		for(int i = 0; i < 5; i++)
			if(i+3 < 5)
				System.out.print(utm[i][i+3]+" ");

		System.out.println();
		for(int i = 0; i < 5; i++)
			if(i+4 < 5)
				System.out.print(utm[i][i+4]+" ");*/
		
		for(int k = 1; k <= r; k++) {
			System.out.println();
			for(int i = 0; i < c; i++) {
				int j = i+k-1;
				if(j < c) {
					System.out.print(utm[i][j]+" ");
					if(i != j) {
						System.out.print("(");
						for(int l = i; l < j; l++) {
							System.out.print(i+""+l+"+"+(l+1)+j+" ");
						}
						System.out.print(")");
					}
				}
			}
		}

	}

}
