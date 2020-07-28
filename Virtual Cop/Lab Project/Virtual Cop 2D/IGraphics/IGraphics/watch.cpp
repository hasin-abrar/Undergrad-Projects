
# include "iGraphics.h"
# include<math.h>
# include<time.h>

int k=0;
int h1,h2,m1,m2,s1,s2;
int H1,M1,S1;
double o,x=500,y=360+250,sx,sy,p;
double xm=500,ym=360+220,mx,my,pm;
double xh=500,yh=360+200,hx,hy,ph,H,M,S;
int i;
/* 
	function iDraw() is called again and again by the system.
*/
void iDraw()
{
	//place your drawing codes here	
	iClear();
	if (k==0)
	{
	
		iClear();
	//iSetColor(0,810,160);fa
	//iFilledRectangle(505,260,7,15);
	//iFilledRectangle(505,310,7,15);

	//iFilledRectangle(867,260,7,15);
	
	//iFilledRectangle(867,310,7,15);
	
		iSetColor(34,198,91);
		iText(10, 10, "Press A for ANALOG CLOCK and E for EXIT");
	
		if(h1 == 0)
		{
			iSetColor(0,80,160);
			iFilledRectangle(200,200,5,100);
			iFilledRectangle(200,305,5,100);
			iFilledRectangle(205,195,80,5);
			iFilledRectangle(205,405,80,5);
			iFilledRectangle(200,200,5,100);
			iFilledRectangle(200,305,5,100);
			iFilledRectangle(285,200,5,100);
			iFilledRectangle(285,305,5,100);
		}

	
		if (h1 == 1)
		{
			iSetColor(0,80,160);
			iFilledRectangle(285,200,5,100);
			iFilledRectangle(285,305,5,100);
		}
		if(h1 == 2)
		{
			iSetColor(0,80,160);
			iFilledRectangle(205,195,80,5);
			iFilledRectangle(205,405,80,5);
			iFilledRectangle(205,300,80,5);
			iFilledRectangle(200,200,5,100);
			iFilledRectangle(285,305,5,100);
		}

		
		if(h2 == 0)
		{
			iSetColor(0,80,160);
			iFilledRectangle(375,195,80,5);
			iFilledRectangle(375,405,80,5);
			iFilledRectangle(370,200,5,100);
			iFilledRectangle(455,305,5,100);
	
			iFilledRectangle(370,305,5,100);
			iFilledRectangle(455,200,5,100);
		
		}
		if  (h2 == 1)
		{
			iSetColor(0,80,160);
			iFilledRectangle(455,305,5,100);
			iFilledRectangle(455,200,5,100);
		}
	
		if  (h2 == 2)
		{
	
			iSetColor(0,80,160);
			iFilledRectangle(375,195,80,5);
			iFilledRectangle(375,300,80,5);
			iFilledRectangle(375,405,80,5);
			iFilledRectangle(455,305,5,100);
			iFilledRectangle(370,200,5,100);

		}
	
		if  (h2 == 3)
		{
			iSetColor(0,80,160);
			iFilledRectangle(375,195,80,5);
			iFilledRectangle(375,300,80,5);
			iFilledRectangle(375,405,80,5);
			iFilledRectangle(455,305,5,100);
			iFilledRectangle(455,200,5,100);
	
		}
		if  (h2 == 4)
		{
			iSetColor(0,80,160);
			iFilledRectangle(375,300,80,5);
			iFilledRectangle(370,305,5,100);
			iFilledRectangle(455,305,5,100);
			iFilledRectangle(455,200,5,100);
		}
		if  (h2 == 5)
		{
		
			iSetColor(0,80,160);
			iFilledRectangle(375,195,80,5);
			iFilledRectangle(375,300,80,5);
			iFilledRectangle(375,405,80,5);
			iFilledRectangle(370,305,5,100);
			iFilledRectangle(455,200,5,100);
	
		}
		if  (h2 == 6)
		{
	
			iSetColor(0,80,160);
	
			iFilledRectangle(375,195,80,5);
			iFilledRectangle(375,300,80,5);
			iFilledRectangle(375,405,80,5);
			iFilledRectangle(370,305,5,100);
			iFilledRectangle(455,200,5,100);
			iFilledRectangle(370,200,5,100);

		}
		if  (h2 == 7)
		{
			iSetColor(0,80,160);
			iFilledRectangle(455,305,5,100);
			iFilledRectangle(455,200,5,100);
	
			iFilledRectangle(375,405,80,5);


		}
		if  (h2 == 8)
		{
			iSetColor(0,80,160);

			iFilledRectangle(375,195,80,5);
			iFilledRectangle(375,300,80,5);
			iFilledRectangle(375,405,80,5);
			iFilledRectangle(370,200,5,100);
			iFilledRectangle(455,305,5,100);
	
			iFilledRectangle(370,305,5,100);
			iFilledRectangle(455,200,5,100);
		}
		if  (h2 == 9)
		{
			iSetColor(0,80,160);
			iFilledRectangle(375,195,80,5);
			iFilledRectangle(375,300,80,5);
			iFilledRectangle(375,405,80,5);
	
			iFilledRectangle(455,305,5,100);
	
			iFilledRectangle(370,305,5,100);
			iFilledRectangle(455,200,5,100);
		}

		if(m1 == 0)
		{
	
			iSetColor(0,80,160);
			iFilledRectangle(567,195,80,5);
			iFilledRectangle(567,405,80,5);

			iFilledRectangle(562,200,5,100);
			iFilledRectangle(562,305,5,100);

			iFilledRectangle(647,200,5,100);
			iFilledRectangle(647,305,5,100);
		}

		if (m1 == 1)
		{
			iSetColor(0,80,160);
			iFilledRectangle(647,200,5,100);
			iFilledRectangle(647,305,5,100);

	
		}
	
		if (m1 == 2)
		{
			iSetColor(0,80,160);
			iFilledRectangle(567,195,80,5);
			iFilledRectangle(567,405,80,5);
			iFilledRectangle(567,300,80,5);
			iFilledRectangle(562,200,5,100);
			iFilledRectangle(647,305,5,100);

		}
	
		if (m1 == 3)
		{
			iSetColor(0,80,160);
			iFilledRectangle(567,195,80,5);
			iFilledRectangle(567,405,80,5);
			iFilledRectangle(567,300,80,5);
			iFilledRectangle(647,200,5,100);
			iFilledRectangle(647,305,5,100);
		}
	
		if (m1 == 4)
		{
			iSetColor(0,80,160);

			iFilledRectangle(647,200,5,100);
			iFilledRectangle(647,305,5,100);
			iFilledRectangle(567,300,80,5);
			iFilledRectangle(562,305,5,100);
		}
	
		if (m1 == 5)
		{
			iSetColor(0,80,160);
			iFilledRectangle(567,195,80,5);
			iFilledRectangle(567,405,80,5);
			iFilledRectangle(567,300,80,5);
			iFilledRectangle(562,305,5,100);
			iFilledRectangle(647,200,5,100);

		}
	
		if (m1 == 6)
		{
	
			iSetColor(0,80,160);

			iFilledRectangle(567,195,80,5);
			iFilledRectangle(567,405,80,5);
			iFilledRectangle(567,300,80,5);
			iFilledRectangle(562,305,5,100);
			iFilledRectangle(647,200,5,100);
			iFilledRectangle(562,200,5,100);	
		}
	
		if (m1 == 7)
		{
			iSetColor(0,80,160);
			iFilledRectangle(647,200,5,100);
			iFilledRectangle(647,305,5,100);
			iFilledRectangle(567,405,80,5);	
		}
	
		if (m1 == 8)
		{
			iSetColor(0,80,160);
			iFilledRectangle(647,305,5,100);
			iFilledRectangle(567,195,80,5);
			iFilledRectangle(567,405,80,5);
			iFilledRectangle(567,300,80,5);
			iFilledRectangle(562,305,5,100);
			iFilledRectangle(647,200,5,100);
			iFilledRectangle(562,200,5,100);

	
		}
	
		if (m1 == 9)
		{
	
			iSetColor(0,80,160);
			iFilledRectangle(647,305,5,100);
			iFilledRectangle(567,195,80,5);
			iFilledRectangle(567,405,80,5);
			iFilledRectangle(567,300,80,5);
			iFilledRectangle(562,305,5,100);
			iFilledRectangle(647,200,5,100);	
		}



		if(m2 == 0)
		{
	
			iSetColor(0,80,160);
			iFilledRectangle(737,195,80,5);

			iFilledRectangle(737,405,80,5);
	
			iFilledRectangle(732,200,5,100);
			iFilledRectangle(732,305,5,100);

	
			iFilledRectangle(817,200,5,100);
			iFilledRectangle(817,305,5,100);
			
		}

		if (m2 == 1)
		{	iSetColor(0,80,160);
			iFilledRectangle(817,200,5,100);

	
			iFilledRectangle(817,305,5,100);

	
		}
		if (m2 == 2)
		{	iSetColor(0,80,160);
			iFilledRectangle(737,195,80,5);
			iFilledRectangle(737,300,80,5);
			iFilledRectangle(737,405,80,5);
	
			iFilledRectangle(732,200,5,100);

	
			iFilledRectangle(817,305,5,100);

	
		}
		if (m2 == 3)
		{
			iSetColor(0,80,160);

			iFilledRectangle(737,195,80,5);
			iFilledRectangle(737,300,80,5);
			iFilledRectangle(737,405,80,5);
	
			iFilledRectangle(817,200,5,100);

	
			iFilledRectangle(817,305,5,100);
		}
		if (m2 == 4)
		{	iSetColor(0,80,160);	
			iFilledRectangle(737,300,80,5);
			iFilledRectangle(732,305,5,100);

			iFilledRectangle(817,200,5,100);
			iFilledRectangle(817,305,5,100);
	
		}
		if (m2 == 5)
		{
			iSetColor(0,80,160);
			iFilledRectangle(737,195,80,5);
			iFilledRectangle(737,300,80,5);
			iFilledRectangle(737,405,80,5);
	
			iFilledRectangle(732,305,5,100);

	
			iFilledRectangle(817,200,5,100);
		}
		if (m2 == 6)
		{
			iSetColor(0,80,160);
			iFilledRectangle(737,195,80,5);
			iFilledRectangle(737,300,80,5);
			iFilledRectangle(737,405,80,5);
	
			iFilledRectangle(732,200,5,100);
			iFilledRectangle(732,305,5,100);

	
			iFilledRectangle(817,200,5,100);
		}
		if (m2 == 7)
		{
			iSetColor(0,80,160);
			iFilledRectangle(737,405,80,5);
	
			iFilledRectangle(817,200,5,100);

	
			iFilledRectangle(817,305,5,100);
		}
		if (m2 == 8)
		{
			iSetColor(0,80,160);
			iFilledRectangle(737,195,80,5);
			iFilledRectangle(737,300,80,5);
			iFilledRectangle(737,405,80,5);
	
			iFilledRectangle(732,200,5,100);
			iFilledRectangle(732,305,5,100);

	
			iFilledRectangle(817,200,5,100);
			iFilledRectangle(817,305,5,100);
		}
		if (m2 == 9)
		{
			iSetColor(0,80,160);
			iFilledRectangle(737,195,80,5);
			iFilledRectangle(737,300,80,5);
			iFilledRectangle(737,405,80,5);
	
	
			iFilledRectangle(732,305,5,100);

	
			iFilledRectangle(817,200,5,100);
			iFilledRectangle(817,305,5,100);

		}




		if(s1 == 0)

		{
			iSetColor(0,80,160);
			iFilledRectangle(927,195,80,5);
	
			iFilledRectangle(927,405,80,5);

			iFilledRectangle(922,200,5,100);
			iFilledRectangle(922,305,5,100);
	
			iFilledRectangle(1007,200,5,100);
			iFilledRectangle(1007,305,5,100);

		}
	
		if (s1 == 1)
		{
			iSetColor(0,80,160);
			iFilledRectangle(1007,200,5,100);
			iFilledRectangle(1007,305,5,100);

		}
		if (s1 == 2)
		{
	
			iSetColor(0,80,160);
			iFilledRectangle(927,195,80,5);
			iFilledRectangle(927,300,80,5);
			iFilledRectangle(927,405,80,5);


	
	
			iFilledRectangle(922,200,5,100);
			iFilledRectangle(1007,305,5,100);

		}
		if (s1 == 3)
		{
			iSetColor(0,80,160);
			iFilledRectangle(927,195,80,5);
			iFilledRectangle(927,300,80,5);
			iFilledRectangle(927,405,80,5);
			iFilledRectangle(1007,200,5,100);
			iFilledRectangle(1007,305,5,100);

		}
		if (s1 == 4)
		{
			iSetColor(0,80,160);
			iFilledRectangle(927,300,80,5);
	
			iFilledRectangle(922,305,5,100);

			iFilledRectangle(1007,305,5,100);
	
			iFilledRectangle(1007,200,5,100);
		}
		if (s1 == 5)
		{
			iSetColor(0,80,160);
			iFilledRectangle(927,195,80,5);
			iFilledRectangle(927,300,80,5);
			iFilledRectangle(927,405,80,5);

			iFilledRectangle(922,305,5,100);
	
			iFilledRectangle(1007,200,5,100);

		}
		if (s1 == 6)
		{
			iSetColor(0,80,160);
			iFilledRectangle(927,195,80,5);
			iFilledRectangle(927,300,80,5);
			iFilledRectangle(927,405,80,5);

			iFilledRectangle(922,200,5,100);
			iFilledRectangle(922,305,5,100);
	
			iFilledRectangle(1007,200,5,100);
		}
		if (s1 == 7)
		{
			iSetColor(0,80,160);
			iFilledRectangle(927,405,80,5);
			iFilledRectangle(1007,200,5,100);
			iFilledRectangle(1007,305,5,100);

		}
		if (s1 == 8)
		{
			iSetColor(0,80,160);
			iFilledRectangle(927,195,80,5);
			iFilledRectangle(927,300,80,5);
			iFilledRectangle(927,405,80,5);

			iFilledRectangle(922,200,5,100);
			iFilledRectangle(922,305,5,100);
	
			iFilledRectangle(1007,200,5,100);
			iFilledRectangle(1007,305,5,100);
	
		}
		if (s1 == 9)
		{	iSetColor(0,80,160);
			iFilledRectangle(927,195,80,5);
			iFilledRectangle(927,300,80,5);
			iFilledRectangle(927,405,80,5);


			iFilledRectangle(922,305,5,100);
	
			iFilledRectangle(1007,200,5,100);
			iFilledRectangle(1007,305,5,100);

	
		}
		if(s2 == 0)

		{
			iSetColor(0,80,160);
			iFilledRectangle(1097,195,80,5);
	
			iFilledRectangle(1097,405,80,5);

			iFilledRectangle(1092,200,5,100);
			iFilledRectangle(1092,305,5,100);
	
			iFilledRectangle(1177,200,5,100);
			iFilledRectangle(1177,305,5,100);

		}

		if (s2 == 1)
		{
			iSetColor(0,80,160);
			iFilledRectangle(1177,200,5,100);
			iFilledRectangle(1177,305,5,100);
			iSetColor(0,80,160);
			iFilledRectangle(505,260,7,15);
			iFilledRectangle(505,310,7,15);

			iFilledRectangle(867,260,7,15);
	
			iFilledRectangle(867,310,7,15);
	
		}
		if (s2 == 2)
		{
			iSetColor(0,80,160);
			iFilledRectangle(1097,195,80,5);
			iFilledRectangle(1097,300,80,5);
			iFilledRectangle(1097,405,80,5);

			iFilledRectangle(1177,305,5,100);
	
			iFilledRectangle(1092,200,5,100);
		}
		if (s2== 3)
		{
			iSetColor(0,80,160);
			iFilledRectangle(1097,195,80,5);
			iFilledRectangle(1097,300,80,5);
			iFilledRectangle(1097,405,80,5);

			iFilledRectangle(1177,305,5,100);
	
			iFilledRectangle(1177,200,5,100);
			iSetColor(0,80,160);
			iFilledRectangle(505,260,7,15);
			iFilledRectangle(505,310,7,15);

			iFilledRectangle(867,260,7,15);
	
			iFilledRectangle(867,310,7,15);
	
	
		}
		if (s2 == 4)
		{
			iSetColor(0,80,160);
			iFilledRectangle(1092,305,5,100);
			iFilledRectangle(1097,300,80,5);
			iFilledRectangle(1177,200,5,100);
			iFilledRectangle(1177,305,5,100);
		}
		if (s2 == 5)
		{
			iSetColor(0,80,160);
			iFilledRectangle(1097,195,80,5);
			iFilledRectangle(1097,300,80,5);
			iFilledRectangle(1097,405,80,5);

			iFilledRectangle(1092,305,5,100);
	
			iFilledRectangle(1177,200,5,100);
			iSetColor(0,80,160);
			iFilledRectangle(505,260,7,15);
			iFilledRectangle(505,310,7,15);

			iFilledRectangle(867,260,7,15);
	
			iFilledRectangle(867,310,7,15);
	
		}
		if (s2 == 6)
		{
			iSetColor(0,80,160);
			iFilledRectangle(1097,195,80,5);
			iFilledRectangle(1097,300,80,5);
			iFilledRectangle(1097,405,80,5);

			iFilledRectangle(1092,200,5,100);
			iFilledRectangle(1092,305,5,100);
	
			iFilledRectangle(1177,200,5,100);
		}
		if (s2 == 7)
		{
			iSetColor(0,80,160);
			iFilledRectangle(1097,405,80,5);
			iFilledRectangle(1177,200,5,100);
			iFilledRectangle(1177,305,5,100);
			iSetColor(0,80,160);
			iFilledRectangle(505,260,7,15);
			iFilledRectangle(505,310,7,15);

			iFilledRectangle(867,260,7,15);
	
			iFilledRectangle(867,310,7,15);
	
		}
		if (s2 == 8)
		{
			iSetColor(0,80,160);
			iFilledRectangle(1097,195,80,5);
			iFilledRectangle(1097,300,80,5);
			iFilledRectangle(1097,405,80,5);

			iFilledRectangle(1092,200,5,100);
			iFilledRectangle(1092,305,5,100);
	
			iFilledRectangle(1177,200,5,100);
			iFilledRectangle(1177,305,5,100);
	
			}
		if (s2 == 9)
		{
			iSetColor(0,80,160);
			iFilledRectangle(1097,195,80,5);
			iFilledRectangle(1097,300,80,5);
			iFilledRectangle(1097,405,80,5);

	
			iFilledRectangle(1092,305,5,100);
	
			iFilledRectangle(1177,200,5,100);
			iFilledRectangle(1177,305,5,100);
			iSetColor(0,80,160);
			iFilledRectangle(505,260,7,15);
			iFilledRectangle(505,310,7,15);

			iFilledRectangle(867,260,7,15);
	
			iFilledRectangle(867,310,7,15);
	
		}
	}
	if (k==1)
	{
	iClear();
	//iSetColor(0,810,160);
	//iFilledRectangle(505,260,7,15);
	//iFilledRectangle(505,310,7,15);

	//iFilledRectangle(867,260,7,15);
	
	//iFilledRectangle(867,310,7,15);
	
		iSetColor(34,198,91);
		iText(10, 10, "Press A for ANALOG CLOCK and E for EXIT");
	
		if(h1 == 0)
		{
			iSetColor(0,80,160);
			iFilledRectangle(200,200,5,100);
			iFilledRectangle(200,305,5,100);
			iFilledRectangle(205,195,80,5);
			iFilledRectangle(205,405,80,5);
			iFilledRectangle(200,200,5,100);
			iFilledRectangle(200,305,5,100);
			iFilledRectangle(285,200,5,100);
			iFilledRectangle(285,305,5,100);
		}

	
		if (h1 == 1)
		{
			iSetColor(0,80,160);
			iFilledRectangle(285,200,5,100);
			iFilledRectangle(285,305,5,100);
		}
		if(h1 == 2)
		{
			iSetColor(0,80,160);
			iFilledRectangle(205,195,80,5);
			iFilledRectangle(205,405,80,5);
			iFilledRectangle(205,300,80,5);
			iFilledRectangle(200,200,5,100);
			iFilledRectangle(285,305,5,100);
		}

		
		if(h2 == 0)
		{
			iSetColor(0,80,160);
			iFilledRectangle(375,195,80,5);
			iFilledRectangle(375,405,80,5);
			iFilledRectangle(370,200,5,100);
			iFilledRectangle(455,305,5,100);
	
			iFilledRectangle(370,305,5,100);
			iFilledRectangle(455,200,5,100);
		
		}
		if  (h2 == 1)
		{
			iSetColor(0,80,160);
			iFilledRectangle(455,305,5,100);
			iFilledRectangle(455,200,5,100);
		}
	
		if  (h2 == 2)
		{
	
			iSetColor(0,80,160);
			iFilledRectangle(375,195,80,5);
			iFilledRectangle(375,300,80,5);
			iFilledRectangle(375,405,80,5);
			iFilledRectangle(455,305,5,100);
			iFilledRectangle(370,200,5,100);

		}
	
		if  (h2 == 3)
		{
			iSetColor(0,80,160);
			iFilledRectangle(375,195,80,5);
			iFilledRectangle(375,300,80,5);
			iFilledRectangle(375,405,80,5);
			iFilledRectangle(455,305,5,100);
			iFilledRectangle(455,200,5,100);
	
		}
		if  (h2 == 4)
		{
			iSetColor(0,80,160);
			iFilledRectangle(375,300,80,5);
			iFilledRectangle(370,305,5,100);
			iFilledRectangle(455,305,5,100);
			iFilledRectangle(455,200,5,100);
		}
		if  (h2 == 5)
		{
		
			iSetColor(0,80,160);
			iFilledRectangle(375,195,80,5);
			iFilledRectangle(375,300,80,5);
			iFilledRectangle(375,405,80,5);
			iFilledRectangle(370,305,5,100);
			iFilledRectangle(455,200,5,100);
	
		}
		if  (h2 == 6)
		{
	
			iSetColor(0,80,160);
	
			iFilledRectangle(375,195,80,5);
			iFilledRectangle(375,300,80,5);
			iFilledRectangle(375,405,80,5);
			iFilledRectangle(370,305,5,100);
			iFilledRectangle(455,200,5,100);
			iFilledRectangle(370,200,5,100);

		}
		if  (h2 == 7)
		{
			iSetColor(0,80,160);
			iFilledRectangle(455,305,5,100);
			iFilledRectangle(455,200,5,100);
	
			iFilledRectangle(375,405,80,5);


		}
		if  (h2 == 8)
		{
			iSetColor(0,80,160);

			iFilledRectangle(375,195,80,5);
			iFilledRectangle(375,300,80,5);
			iFilledRectangle(375,405,80,5);
			iFilledRectangle(370,200,5,100);
			iFilledRectangle(455,305,5,100);
	
			iFilledRectangle(370,305,5,100);
			iFilledRectangle(455,200,5,100);
		}
		if  (h2 == 9)
		{
			iSetColor(0,80,160);
			iFilledRectangle(375,195,80,5);
			iFilledRectangle(375,300,80,5);
			iFilledRectangle(375,405,80,5);
	
			iFilledRectangle(455,305,5,100);
	
			iFilledRectangle(370,305,5,100);
			iFilledRectangle(455,200,5,100);
		}

		if(m1 == 0)
		{
	
			iSetColor(0,80,160);
			iFilledRectangle(567,195,80,5);
			iFilledRectangle(567,405,80,5);

			iFilledRectangle(562,200,5,100);
			iFilledRectangle(562,305,5,100);

			iFilledRectangle(647,200,5,100);
			iFilledRectangle(647,305,5,100);
		}

		if (m1 == 1)
		{
			iSetColor(0,80,160);
			iFilledRectangle(647,200,5,100);
			iFilledRectangle(647,305,5,100);

	
		}
	
		if (m1 == 2)
		{
			iSetColor(0,80,160);
			iFilledRectangle(567,195,80,5);
			iFilledRectangle(567,405,80,5);
			iFilledRectangle(567,300,80,5);
			iFilledRectangle(562,200,5,100);
			iFilledRectangle(647,305,5,100);

		}
	
		if (m1 == 3)
		{
			iSetColor(0,80,160);
			iFilledRectangle(567,195,80,5);
			iFilledRectangle(567,405,80,5);
			iFilledRectangle(567,300,80,5);
			iFilledRectangle(647,200,5,100);
			iFilledRectangle(647,305,5,100);
		}
	
		if (m1 == 4)
		{
			iSetColor(0,80,160);

			iFilledRectangle(647,200,5,100);
			iFilledRectangle(647,305,5,100);
			iFilledRectangle(567,300,80,5);
			iFilledRectangle(562,305,5,100);
		}
	
		if (m1 == 5)
		{
			iSetColor(0,80,160);
			iFilledRectangle(567,195,80,5);
			iFilledRectangle(567,405,80,5);
			iFilledRectangle(567,300,80,5);
			iFilledRectangle(562,305,5,100);
			iFilledRectangle(647,200,5,100);

		}
	
		if (m1 == 6)
		{
	
			iSetColor(0,80,160);

			iFilledRectangle(567,195,80,5);
			iFilledRectangle(567,405,80,5);
			iFilledRectangle(567,300,80,5);
			iFilledRectangle(562,305,5,100);
			iFilledRectangle(647,200,5,100);
			iFilledRectangle(562,200,5,100);	
		}
	
		if (m1 == 7)
		{
			iSetColor(0,80,160);
			iFilledRectangle(647,200,5,100);
			iFilledRectangle(647,305,5,100);
			iFilledRectangle(567,405,80,5);	
		}
	
		if (m1 == 8)
		{
			iSetColor(0,80,160);
			iFilledRectangle(647,305,5,100);
			iFilledRectangle(567,195,80,5);
			iFilledRectangle(567,405,80,5);
			iFilledRectangle(567,300,80,5);
			iFilledRectangle(562,305,5,100);
			iFilledRectangle(647,200,5,100);
			iFilledRectangle(562,200,5,100);

	
		}
	
		if (m1 == 9)
		{
	
			iSetColor(0,80,160);
			iFilledRectangle(647,305,5,100);
			iFilledRectangle(567,195,80,5);
			iFilledRectangle(567,405,80,5);
			iFilledRectangle(567,300,80,5);
			iFilledRectangle(562,305,5,100);
			iFilledRectangle(647,200,5,100);	
		}



		if(m2 == 0)
		{
	
			iSetColor(0,80,160);
			iFilledRectangle(737,195,80,5);

			iFilledRectangle(737,405,80,5);
	
			iFilledRectangle(732,200,5,100);
			iFilledRectangle(732,305,5,100);

	
			iFilledRectangle(817,200,5,100);
			iFilledRectangle(817,305,5,100);
			
		}

		if (m2 == 1)
		{	iSetColor(0,80,160);
			iFilledRectangle(817,200,5,100);

	
			iFilledRectangle(817,305,5,100);

	
		}
		if (m2 == 2)
		{	iSetColor(0,80,160);
			iFilledRectangle(737,195,80,5);
			iFilledRectangle(737,300,80,5);
			iFilledRectangle(737,405,80,5);
	
			iFilledRectangle(732,200,5,100);

	
			iFilledRectangle(817,305,5,100);

	
		}
		if (m2 == 3)
		{
			iSetColor(0,80,160);

			iFilledRectangle(737,195,80,5);
			iFilledRectangle(737,300,80,5);
			iFilledRectangle(737,405,80,5);
	
			iFilledRectangle(817,200,5,100);

	
			iFilledRectangle(817,305,5,100);
		}
		if (m2 == 4)
		{	iSetColor(0,80,160);	
			iFilledRectangle(737,300,80,5);
			iFilledRectangle(732,305,5,100);

			iFilledRectangle(817,200,5,100);
			iFilledRectangle(817,305,5,100);
	
		}
		if (m2 == 5)
		{
			iSetColor(0,80,160);
			iFilledRectangle(737,195,80,5);
			iFilledRectangle(737,300,80,5);
			iFilledRectangle(737,405,80,5);
	
			iFilledRectangle(732,305,5,100);

	
			iFilledRectangle(817,200,5,100);
		}
		if (m2 == 6)
		{
			iSetColor(0,80,160);
			iFilledRectangle(737,195,80,5);
			iFilledRectangle(737,300,80,5);
			iFilledRectangle(737,405,80,5);
	
			iFilledRectangle(732,200,5,100);
			iFilledRectangle(732,305,5,100);

	
			iFilledRectangle(817,200,5,100);
		}
		if (m2 == 7)
		{
			iSetColor(0,80,160);
			iFilledRectangle(737,405,80,5);
	
			iFilledRectangle(817,200,5,100);

	
			iFilledRectangle(817,305,5,100);
		}
		if (m2 == 8)
		{
			iSetColor(0,80,160);
			iFilledRectangle(737,195,80,5);
			iFilledRectangle(737,300,80,5);
			iFilledRectangle(737,405,80,5);
	
			iFilledRectangle(732,200,5,100);
			iFilledRectangle(732,305,5,100);

	
			iFilledRectangle(817,200,5,100);
			iFilledRectangle(817,305,5,100);
		}
		if (m2 == 9)
		{
			iSetColor(0,80,160);
			iFilledRectangle(737,195,80,5);
			iFilledRectangle(737,300,80,5);
			iFilledRectangle(737,405,80,5);
	
	
			iFilledRectangle(732,305,5,100);

	
			iFilledRectangle(817,200,5,100);
			iFilledRectangle(817,305,5,100);

		}




		if(s1 == 0)

		{
			iSetColor(0,80,160);
			iFilledRectangle(927,195,80,5);
	
			iFilledRectangle(927,405,80,5);

			iFilledRectangle(922,200,5,100);
			iFilledRectangle(922,305,5,100);
	
			iFilledRectangle(1007,200,5,100);
			iFilledRectangle(1007,305,5,100);

		}
	
		if (s1 == 1)
		{
			iSetColor(0,80,160);
			iFilledRectangle(1007,200,5,100);
			iFilledRectangle(1007,305,5,100);

		}
		if (s1 == 2)
		{
	
			iSetColor(0,80,160);
			iFilledRectangle(927,195,80,5);
			iFilledRectangle(927,300,80,5);
			iFilledRectangle(927,405,80,5);


	
	
			iFilledRectangle(922,200,5,100);
			iFilledRectangle(1007,305,5,100);

		}
		if (s1 == 3)
		{
			iSetColor(0,80,160);
			iFilledRectangle(927,195,80,5);
			iFilledRectangle(927,300,80,5);
			iFilledRectangle(927,405,80,5);
			iFilledRectangle(1007,200,5,100);
			iFilledRectangle(1007,305,5,100);

		}
		if (s1 == 4)
		{
			iSetColor(0,80,160);
			iFilledRectangle(927,300,80,5);
	
			iFilledRectangle(922,305,5,100);

			iFilledRectangle(1007,305,5,100);
	
			iFilledRectangle(1007,200,5,100);
		}
		if (s1 == 5)
		{
			iSetColor(0,80,160);
			iFilledRectangle(927,195,80,5);
			iFilledRectangle(927,300,80,5);
			iFilledRectangle(927,405,80,5);

			iFilledRectangle(922,305,5,100);
	
			iFilledRectangle(1007,200,5,100);

		}
		if (s1 == 6)
		{
			iSetColor(0,80,160);
			iFilledRectangle(927,195,80,5);
			iFilledRectangle(927,300,80,5);
			iFilledRectangle(927,405,80,5);

			iFilledRectangle(922,200,5,100);
			iFilledRectangle(922,305,5,100);
	
			iFilledRectangle(1007,200,5,100);
		}
		if (s1 == 7)
		{
			iSetColor(0,80,160);
			iFilledRectangle(927,405,80,5);
			iFilledRectangle(1007,200,5,100);
			iFilledRectangle(1007,305,5,100);

		}
		if (s1 == 8)
		{
			iSetColor(0,80,160);
			iFilledRectangle(927,195,80,5);
			iFilledRectangle(927,300,80,5);
			iFilledRectangle(927,405,80,5);

			iFilledRectangle(922,200,5,100);
			iFilledRectangle(922,305,5,100);
	
			iFilledRectangle(1007,200,5,100);
			iFilledRectangle(1007,305,5,100);
	
		}
		if (s1 == 9)
		{	iSetColor(0,80,160);
			iFilledRectangle(927,195,80,5);
			iFilledRectangle(927,300,80,5);
			iFilledRectangle(927,405,80,5);


			iFilledRectangle(922,305,5,100);
	
			iFilledRectangle(1007,200,5,100);
			iFilledRectangle(1007,305,5,100);

	
		}
		if(s2 == 0)

		{
			iSetColor(0,80,160);
			iFilledRectangle(1097,195,80,5);
	
			iFilledRectangle(1097,405,80,5);

			iFilledRectangle(1092,200,5,100);
			iFilledRectangle(1092,305,5,100);
	
			iFilledRectangle(1177,200,5,100);
			iFilledRectangle(1177,305,5,100);

		}

		if (s2 == 1)
		{
			iSetColor(0,80,160);
			iFilledRectangle(1177,200,5,100);
			iFilledRectangle(1177,305,5,100);
			iSetColor(0,80,160);
			iFilledRectangle(505,260,7,15);
			iFilledRectangle(505,310,7,15);

			iFilledRectangle(867,260,7,15);
	
			iFilledRectangle(867,310,7,15);
	
		}
		if (s2 == 2)
		{
			iSetColor(0,80,160);
			iFilledRectangle(1097,195,80,5);
			iFilledRectangle(1097,300,80,5);
			iFilledRectangle(1097,405,80,5);

			iFilledRectangle(1177,305,5,100);
	
			iFilledRectangle(1092,200,5,100);
		}
		if (s2== 3)
		{
			iSetColor(0,80,160);
			iFilledRectangle(1097,195,80,5);
			iFilledRectangle(1097,300,80,5);
			iFilledRectangle(1097,405,80,5);

			iFilledRectangle(1177,305,5,100);
	
			iFilledRectangle(1177,200,5,100);
			iSetColor(0,80,160);
			iFilledRectangle(505,260,7,15);
			iFilledRectangle(505,310,7,15);

			iFilledRectangle(867,260,7,15);
	
			iFilledRectangle(867,310,7,15);
	
	
		}
		if (s2 == 4)
		{
			iSetColor(0,80,160);
			iFilledRectangle(1092,305,5,100);
			iFilledRectangle(1097,300,80,5);
			iFilledRectangle(1177,200,5,100);
			iFilledRectangle(1177,305,5,100);
		}
		if (s2 == 5)
		{
			iSetColor(0,80,160);
			iFilledRectangle(1097,195,80,5);
			iFilledRectangle(1097,300,80,5);
			iFilledRectangle(1097,405,80,5);

			iFilledRectangle(1092,305,5,100);
	
			iFilledRectangle(1177,200,5,100);
			iSetColor(0,80,160);
			iFilledRectangle(505,260,7,15);
			iFilledRectangle(505,310,7,15);

			iFilledRectangle(867,260,7,15);
	
			iFilledRectangle(867,310,7,15);
	
		}
		if (s2 == 6)
		{
			iSetColor(0,80,160);
			iFilledRectangle(1097,195,80,5);
			iFilledRectangle(1097,300,80,5);
			iFilledRectangle(1097,405,80,5);

			iFilledRectangle(1092,200,5,100);
			iFilledRectangle(1092,305,5,100);
	
			iFilledRectangle(1177,200,5,100);
		}
		if (s2 == 7)
		{
			iSetColor(0,80,160);
			iFilledRectangle(1097,405,80,5);
			iFilledRectangle(1177,200,5,100);
			iFilledRectangle(1177,305,5,100);
			iSetColor(0,80,160);
			iFilledRectangle(505,260,7,15);
			iFilledRectangle(505,310,7,15);

			iFilledRectangle(867,260,7,15);
	
			iFilledRectangle(867,310,7,15);
	
		}
		if (s2 == 8)
		{
			iSetColor(0,80,160);
			iFilledRectangle(1097,195,80,5);
			iFilledRectangle(1097,300,80,5);
			iFilledRectangle(1097,405,80,5);

			iFilledRectangle(1092,200,5,100);
			iFilledRectangle(1092,305,5,100);
	
			iFilledRectangle(1177,200,5,100);
			iFilledRectangle(1177,305,5,100);
	
			}
		if (s2 == 9)
		{
			iSetColor(0,80,160);
			iFilledRectangle(1097,195,80,5);
			iFilledRectangle(1097,300,80,5);
			iFilledRectangle(1097,405,80,5);

	
			iFilledRectangle(1092,305,5,100);
	
			iFilledRectangle(1177,200,5,100);
			iFilledRectangle(1177,305,5,100);
			iSetColor(0,80,160);
			iFilledRectangle(505,260,7,15);
			iFilledRectangle(505,310,7,15);

			iFilledRectangle(867,260,7,15);
	
			iFilledRectangle(867,310,7,15);
	
		}
	}
	if(k==2)
	{
		//iSetColor(154,220,114);
		//iFilledCircle(500,360,310);//Large
		      //iSetColor(0,255,0);
		     //iCircle(500,360,305);//out
		iSetColor(255,255,255);
		iCircle(500,360,275);//nextout
		iSetColor(255,0,0);
		for (i=1,o=(6/57.29);i<=60;i++,o+=(6/57.29))
		{   if(i%5!=0)
			{
				iFilledCircle(500+295*cos(o),360+295*sin(o),2);
		    }
			if (i%5==0)
			{
				iFilledCircle(0,0,0);
			}
			
		}
		iText(10, 10, "Press D for DIGITAL CLOCK and E for EXIT");

		iSetColor(255,255,255);
		iLine(500,360,x,y);///second
		iSetColor(255,0,0);
		iLine(500,360,xm,ym);///minit
		iSetColor(20,50,210);
		iLine(500,360,xh,yh);//HR
		iSetColor(20,50,210);
		iText(500+249,360-151,"4");//4(500+259,360-150)point
		iText(500+145,360-259,"5");//	iFilledCircle(500+150,360-260,5);//60
		iText(495,60,"6");//iFilledCircle(500,65,5);//lower
		iText(500-155,360-257,"7");//iFilledCircle(500-150,360-260,5);//120
		iText(200,360,"9");//iFilledCircle(205,360,5);//left
		iText(500-265,360+140,"10");//iFilledCircle(500-260,360+150,5);//150
		iText(500+250,360+145,"2");//iFilledCircle(500+259,360+150,5);//330
		iText(500+145,360+252,"1");//	iFilledCircle(500+150,360+260,5);//300
		iText(495,652,"12");//	iFilledCircle(500,655,5);//upper
		iText(788+3,360-3,"3");//	iFilledCircle(795,360,5);//right
		iText(500-155,360+255,"11");//	iFilledCircle(500-150,360+260,5);//240
		iText(500-260,360-150,"8");//	iFilledCircle(500-260,360-150,5);//210
		iSetColor(118,252,18);
		iFilledCircle(500,360,5);//small
	
	
	}

}






/* 
	function iMouseMove() is called when the user presses and drags the mouse.
	(mx, my) is the position where the mouse pointer is.
*/
void iMouseMove(int mx, int my)
{
	//place your codes here
}

/* 
	function iMouse() is called when the user presses/releases the mouse.
	(mx, my) is the position where the mouse pointer is.
*/
void iMouse(int button, int state, int mx, int my)
{
}

/*
	function iKeyboard() is called whenever the user hits a key in keyboard.
	key- holds the ASCII value of the key pressed. 
*/
void iKeyboard(unsigned char key)
{
	if(key == 'd' || key == 'D' )
	{
		k=1;
	}
	if(key == 'a' || key == 'A')
	{
		k=2;
	}

	if(key == 'E' || key == 'e')
	{
		exit(0);	
	
	}
}

/*
	function iSpecialKeyboard() is called whenver user hits special keys like-
	function keys, home, end, pg up, pg down, arraows etc. you have to use 
	appropriate constants to detect them. A list is:
	GLUT_KEY_F1, GLUT_KEY_F2, GLUT_KEY_F3, GLUT_KEY_F4, GLUT_KEY_F5, GLUT_KEY_F6, 
	GLUT_KEY_F7, GLUT_KEY_F8, GLUT_KEY_F9, GLUT_KEY_F10, GLUT_KEY_F11, GLUT_KEY_F12, 
	GLUT_KEY_LEFT, GLUT_KEY_UP, GLUT_KEY_RIGHT, GLUT_KEY_DOWN, GLUT_KEY_PAGE UP, 
	GLUT_KEY_PAGE DOWN, GLUT_KEY_HOME, GLUT_KEY_END, GLUT_KEY_INSERT 
*/
void iSpecialKeyboard(unsigned char key)
{

}

void time()
{

	time_t rawtime;
	struct tm*timeinfo;
	time(&rawtime);
	timeinfo=localtime(&rawtime);
	H1=timeinfo->tm_hour,
	
	M1=timeinfo->tm_min;
	S1=timeinfo->tm_sec;


	
	h1 = H1/10;
	h2 = H1%10;
	m1 = M1/10;
	m2 = M1%10;
	s1 = S1/10;
	s2 = S1%10;

	
	
	

}
void second()
 
{
	  sx=250*cos(p/57.29);
	  sy=250*sin(p/57.29);
	  x=500+sx;
	  y=360+sy;
      p=p-6;
}
void minute()
{
      mx=220*cos(pm/57.29);
	  my=220*sin(pm/57.29);
	  xm=500+mx;
	  ym=360+my;
	  pm=pm-.1;
}
void hour()
{
	hx=200*cos(ph/57.29);
	hy=200*sin(ph/57.29);
	xh=500+hx;
	yh=360+hy;
	ph=ph-1/600;
}


int main()
{
	//iSetTimer(1000, time);
	time_t rawtime;
    tm * ptm;

    time ( &rawtime );

    ptm = gmtime ( &rawtime );   //getting time from bios

    H=(ptm->tm_hour+15)%24;
	M=ptm->tm_min;
	S=(ptm->tm_sec)+2;


	ph =(3-H)*30.0-30*M/60.0-30*S/3600.0+.1/600+270+90;
     
	pm= (15-M)*6.0-S/60.0+.1+270+90;

	p = (15-S)*6.0+6+270+90;

    second();
    minute();
    hour();
	
   
	iSetTimer(1000, time);
    iSetTimer(1000,second);
	iSetTimer(1000,minute);
	iSetTimer(1000,hour);
	
	//iInitialize(1477, 600, "Digital Clock");
	iInitialize(1400, 720,"Digital Clock");
	
	return 0;
}