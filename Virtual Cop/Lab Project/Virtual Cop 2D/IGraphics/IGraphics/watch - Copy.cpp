# include "iGraphics.h"
# include<math.h>
# include<time.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct _player
{
	int score;
	char name[80];
}player[4],temp;
FILE *fp;
char level_array[80];
char villain_count[80];
char str[80],str1[80],_name[80],_score[80],str2[80],tag[20];
char lives[80];
int level = 1,choto_high,boro_high,j;
int score = 0;
int life = 3;
int villain = 0,dekha;
int k=0,check;
int h1,h2,m1,m2,s1,s2;
int H1,M1,S1;
double o,x=500,y=360+250,sx,sy,p;
double xm=500,ym=360+220,mx,my,pm;
double xh=500,yh=360+200,hx,hy,ph,H,M,S;
int i,len,mode,bx,by,dx,dy,val,blood,score_flag=1;
int a,b;
int r,r1,a1,b1,le_down = 0,le_up = 0,ri_down = 0,ri_up;
double mo,cloud1 =150 ,cloud2=960 ;
int mox=0,moy = 1,wing,on=1,highscore,s,h,v,li,le,cloud_1 =1,cloud_2 =1,flag,new_window=0;
int latest = 1; // latest will controll the cover,menu,main game etc etc. 
void random();
void _save();
void _load();
void drawTextBox();
void decrease_misfire();
void mynew();
void _new();
void high_kara();
void high_era();
int foo,doura=0,smoke=0,lx=-100,ly=-100,show;

void iDraw()
{
    
    //iClear();
	if (latest == 1)//on =1 er jaygay
	{
		iShowBMP(0,0,"images//cover1.bmp");// first cover
	
	}

	if(latest == 2)
	{
		iShowBMP(0,0,"images//cover2.bmp");// main menu
	}

	if(latest == 22)
	{
		iShowBMP(0,0,"images//cover3.bmp"); // pause
	}

	if(latest == 4)
	{
		iShowBMP(0,0,"images//story1.bmp");	
	}

	if(latest == 6)
	{
		iShowBMP(0,0,"images//command.bmp");
		
	}

	if(latest == 7)
	{
		iShowBMP(0,0,"images//control1.bmp");	
	}

	if(latest == 29) // dekha ==1 apply kori nai***
	{
		int space =0;
		iShowBMP(0,0,"images//hall_of_fame.bmp");
		iSetColor(255,255,255);
		iText(320,450,"NAME",GLUT_BITMAP_TIMES_ROMAN_24);
		iText(500,450,"SCORE",GLUT_BITMAP_TIMES_ROMAN_24);
		for(i=0;i<3;i++){
			sprintf(tag,"%d .", i+1 );
			iText(320,400-space,tag,GLUT_BITMAP_TIMES_ROMAN_24);
			iText(350,400-space,player[i].name,GLUT_BITMAP_TIMES_ROMAN_24);
			sprintf(_score," %d", player[i].score );
			iText(500,400-space,_score,GLUT_BITMAP_TIMES_ROMAN_24);
			space+=50;
		}

	}

	if(latest == 3|| latest==5){
		dekha =0;//latest er jonno
		
	//place your drawing codes here
		iClear();


		//iShowBMP2(75,602,"images//smoke.bmp",0);


		iSetColor(255,255,255);
		iFilledRectangle(0,0,1068,628);

		//life_dsiplay
		if(life == 3)
		{
			iShowBMP2(75,602,"images//life.bmp",0);
			iShowBMP2(75+25+2,602,"images//life.bmp",0);
			iShowBMP2(75+25+25+2+2,602,"images//life.bmp",0);
		}
		
		else if(life == 2)
		{
			iShowBMP2(75,602,"images//life.bmp",0);
			iShowBMP2(75+25+2,602,"images//life.bmp",0);
				
		}

		else if(life == 1)
		{
			iShowBMP2(75,602,"images//life.bmp",0);
				
		}

			//
		//*********bird fly*******
		wing = 5;
		if(mox% wing==0){
			//iShowBMP(0,0,"images//final building.bmp");
			if(doura==1){
				iShowBMP(mo,532,"images//bird_1.bmp");
				PlaySound("musics\\crow_sound",NULL,SND_ASYNC);
				mo+=55;
			}
			doura =0;
			iShowBMP(mo,532,"images//bird_1.bmp");
			moy++;
			//mo++;
			if(moy%wing==0) mox++;
			mo+=5;
			if(mo>1024) mo = 0;
		}
		else{
			if(doura==1){
				iShowBMP(mo,532,"images//bird_1.bmp");
				mo+=55;
			}
			iShowBMP(mo,532,"images//bird_2.bmp");
			mox++;
			mo+=5;
			if(mox%wing==0) moy++;
			if(mo>1024) mo = 0;
		}

		//misfire_dsiplay

		if(villain == 1)
		{
			iShowBMP2(75,570,"images//misfire.bmp",0);
		}
		
		else if(villain == 2)
		{
			iShowBMP2(75,570,"images//misfire.bmp",0);
			iShowBMP2(75+25+2,570,"images//misfire.bmp",0);
		}

		else if(villain == 3)
		{
			iShowBMP2(75,570,"images//misfire.bmp",0);
			iShowBMP2(75+25+2,570,"images//misfire.bmp",0);
			iShowBMP2(75+25+25+2+2,570,"images//misfire.bmp",0);
		}

			//


		//********Showing everything necessary at top******
			sprintf(str,"Score: %d", score );
			iSetColor(0,0,0);
			iText(850, 600, str);

			/*
			//life
			sprintf(lives,"Life: %d", life );
			iSetColor(0,0,0);
			iText(50, 600, lives);
			sprintf(str1,"Highscore: %d", highscore );
			iText(850, 560, str1);
			*/




			/*
			//villain count
			//sprintf(villain_count,"Villain Escaped: %d", villain );
			sprintf(villain_count,"Misfire: %d", villain );

			iSetColor(0,0,0);
			iText(50, 580, villain_count);
			*/


			iSetColor(0,0,0);
			iText(33, 610, "Life:");

			iSetColor(0,0,0);
			iText(10, 580, "Misfire:");

			//level
			sprintf(level_array,"Level: %d", level );
			iSetColor(0,0,0);
			iText(850, 580, level_array);
			//*******Moving Clouds**********
			iShowBMP2(cloud1,560,"images//cloud2.bmp",0);
			if(cloud_1 == 1){
				cloud1+=0.5;
				if(cloud1 > 500) cloud_1= 2;
			}
			if(cloud_1 ==2) 
			{
				cloud1 -= 0.5;
				//cloud=2;
				if(cloud1< 160) cloud_1 = 1;
			}
			iShowBMP2(602,565,"images//cloud1.bmp",0);

			iShowBMP2(cloud2,560,"images//cloud2.bmp",0);
			if(cloud_2 == 1){
				cloud2-=0.5;
				if(cloud2 < 650) cloud_2= 2;
			}
			if(cloud_2==2) 
			{
				cloud2 += 0.5;
				if(cloud2 > 900) cloud_2 =1;
			}
			iShowBMP(0,0,"images//final building.bmp");
		//iShowBMP(97,442,"images//1.bmp");  //iShowBMP(234,442,"images//1.bmp"); //iShowBMP(373,442,"images//1.bmp"); //iShowBMP(642,442,"images//1.bmp"); //iShowBMP(778,442,"images//1.bmp");  //iShowBMP(911,442,"images//1.bmp");
		//iShowBMP(99,267,"images//1.bmp");  //iShowBMP(236,267,"images//1.bmp"); //iShowBMP(370,267,"images//1.bmp"); //iShowBMP(642,269,"images//1.bmp"); //iShowBMP(778,269,"images//1.bmp");  //iShowBMP(911,269,"images//1.bmp");
		//iShowBMP(91,91,"images//1.bmp");  //iShowBMP(223,91,"images//1.bmp"); //iShowBMP(361,91,"images//1.bmp");  //iShowBMP(634,91,"images//1.bmp"); //iShowBMP(768,91,"images//1.bmp");  //iShowBMP(905,91,"images//1.bmp");
		//A_bmp=> 1,2 = CIVILIAN || 3,4,5 = VILLAIN
		// CIVILIAN VILLAIN EK SATHE DEOA JAY KINA
		if(r == 1)
		{
			iShowBMP(97,442,"images//3.bmp");
		}
		else if(r == 2)
		{
			iShowBMP(234,442,"images//1.bmp");
		}
		else if(r == 3)
		{
			iShowBMP(373,442,"images//3.bmp");
		}
		else if(r == 4)
		{
			iShowBMP(768,91,"images//4.bmp");
		}
		else if(r == 5)
		{
			iShowBMP(642,442,"images//5.bmp");
		}
		else if(r == 6)
		{
			iShowBMP(778,442,"images//1.bmp");
		}
		else  if(r == 7)
		{
			iShowBMP(911,442,"images//1.bmp");
		}
		else  if(r == 8)
		{
			iShowBMP(99,267,"images//4.bmp");
		}
		else  if(r == 9)
		{
			iShowBMP(373,442,"images//3.bmp");
		}
		else if(r == 10)
		{
			iShowBMP(236,267,"images//4.bmp");
		}
		else  if(r == 11)
		{
			iShowBMP(361,91,"images//3.bmp");
		}
		else  if(r == 12)
		{
			iShowBMP(373,442,"images//2.bmp");
		}
		else  if(r == 13)
		{
			iShowBMP(642,442,"images//4.bmp");
		}
		else  if(r == 14)
		{
			iShowBMP(642,442,"images//5.bmp");
		}
		else  if(r == 15)
		{
			iShowBMP(642,269,"images//4.bmp");
		}
		else  if(r == 16)
		{
			iShowBMP(642,269,"images//5.bmp");
		}
		else  if(r == 17)
		{
			iShowBMP(223,91,"images//3.bmp");
		}
		else  if(r == 18)
		{
			iShowBMP(778,269,"images//1.bmp");
		}
		else  if(r == 19)
		{
			iShowBMP(778,442,"images//3.bmp");
		}
		else  if(r == 20)
		{
			iShowBMP(768,91,"images//2.bmp");
		}
		else  if(r == 21)
		{
			iShowBMP(778,269,"images//4.bmp");
		}
		else  if(r == 22)
		{
			iShowBMP(911,442,"images//4.bmp");
		}
		else  if(r == 23)
		{
			iShowBMP(911,442,"images//4.bmp");;
		}
		else  if(r == 24)
		{
			iShowBMP(905,91,"images//5.bmp");
		}
		//iShowBMP(99,267,"images//1.bmp");  //iShowBMP(236,267,"images//1.bmp"); 
		//iShowBMP(370,267,"images//1.bmp"); //iShowBMP(642,269,"images//1.bmp"); 
		//iShowBMP(778,269,"images//1.bmp");  //iShowBMP(911,269,"images//1.bmp");
		else  if(r == 25)
		{
			iShowBMP(911,269,"images//5.bmp");
		}
		else  if(r == 26)
		{
			iShowBMP(99,267,"images//5.bmp");
		}
		else  if(r == 27)
		{
			iShowBMP(905,91,"images//5.bmp");
		}
		else if(r == 28)
		{
			iShowBMP(905,91,"images//5.bmp");

		}
		else if(r == 29)
		{
			iShowBMP(236,267,"images//5.bmp");
		}
		else if(r == 30)
		{
			iShowBMP(236,267,"images//3.bmp");
		}
		else if(r == 31)
		{
			iShowBMP(634,91,"images//2.bmp");
		}
		else if(r == 32)
		{
			iShowBMP(768,91,"images//4.bmp");
		}
		else if(r == 33)
		{
			iShowBMP(370,267,"images//4.bmp");
		}
		else if(r == 34)
		{
			iShowBMP(370,267,"images//3.bmp");
		}
		else if(r == 35)
		{
			iShowBMP(768,91,"images//1.bmp");
		}
		else if(r == 36)
		{
			iShowBMP(370,267,"images//3.bmp");
		}
		else  if(r == 37)
		{
			iShowBMP(642,269,"images//1.bmp");
		}
		else  if(r == 38)
		{
			iShowBMP(91,91,"images//3.bmp");
		}
		else  if(r == 39)
		{
			iShowBMP(642,269,"images//4.bmp");
		}
		else if(r == 40)
		{
			iShowBMP(642,269,"images//4.bmp");
		}
		else  if(r == 41)
		{
			iShowBMP(778,269,"images//4.bmp");
		}
		else  if(r == 42)
		{
			iShowBMP(778,269,"images//4.bmp");
		}
		else  if(r == 43)
		{
			iShowBMP(778,269,"images//4.bmp");
		}
		else  if(r == 44)
		{
			iShowBMP(634,91,"images//2.bmp");
		}
		else  if(r == 45)
		{
			iShowBMP(223,91,"images//4.bmp");
		}
		else  if(r == 46)
		{
			iShowBMP(911,269,"images//4.bmp");
		}
		else  if(r == 47)
		{
			iShowBMP(911,269,"images//5.bmp");
		}
		else  if(r == 48)
		{
			iShowBMP(911,269,"images//5.bmp");
		}
		//iShowBMP(91,91,"images//1.bmp");  //iShowBMP(223,91,"images//1.bmp");
		//iShowBMP(361,91,"images//1.bmp");  //iShowBMP(634,91,"images//1.bmp"); 
		//iShowBMP(768,91,"images//1.bmp");  //iShowBMP(905,91,"images//1.bmp");
		else  if(r == 49)
		{
			iShowBMP(91,91,"images//5.bmp");
		}
		else  if(r == 50)
		{
			iShowBMP(91,91,"images//5.bmp");
		}
		else  if(r == 51)
		{
			iShowBMP(91,91,"images//2.bmp");
		}
		else  if(r == 52)
		{
			iShowBMP(91,91,"images//5.bmp");
		}
		else  if(r == 53)
		{
			iShowBMP(223,91,"images//5.bmp");
		}
		else  if(r == 54)
		{
			iShowBMP(223,91,"images//3.bmp");
		}
		else  if(r == 55)
		{
			iShowBMP(223,91,"images//3.bmp");
		}
		else  if(r == 56)
		{
			iShowBMP(223,91,"images//4.bmp");
		}
		else  if(r == 57)
		{
			iShowBMP(361,91,"images//2.bmp");
		}
		else if(r == 58)
		{
			iShowBMP(361,91,"images//2.bmp");
		}
		else if(r == 59)
		{
			iShowBMP(634,91,"images//2.bmp");
		}
		else if(r == 60)
		{
			iShowBMP(634,91,"images//1.bmp");
		}

		else if(r == 61)
		{
			iShowBMP(97,442,"images//1.bmp");
		}


		//villain count condition
	
		if(villain == 3)
		{
			villain = 0;
			life = life - 1;
		}
		if(le_down){
				iShowBMP(470,0,"images//ledown.bmp");
				if(show==1){
					smoke++;
					show=1;
				}
				iShowBMP2(lx,ly,"images//smoke.bmp",0);
					if(smoke==10){
						lx=-20,ly=-20;
						smoke=0;
						show=0;
						//break;
					}
				
				
				

		}
		else if(le_up){

			iShowBMP(470,0,"images//leup.bmp");
			if(show==1){
					smoke++;
					show=1;
			}
			iShowBMP2(lx,ly,"images//smoke.bmp",0);
			if(smoke==10){
				lx=-20,ly=-20;
				smoke=0;
				show=0;
			}
		}
		else if(ri_down){
			iShowBMP(470,0,"images//ridown.bmp");
			if(show==1){
					smoke++;
					show=1;
			}
			iShowBMP2(lx,ly,"images//smoke.bmp",0);
			if(smoke==10){
				lx=-20,ly=-20;
				smoke=0;
				show=0;
			}
		}
		else {
			iShowBMP(470,0,"images//riup.bmp");
			if(show==1){
					smoke++;
					show=1;
			}
			iShowBMP2(lx,ly,"images//smoke.bmp",0);
			if(smoke==10){
				lx=-20,ly=-20;
				smoke=0;
				show=0;
			}
		}
		//**********BLOOD*************
		int dis1 =5,dis2 =10;
		if(blood==1){
			iSetColor(255,0,0);
			val++;
			if(val<2)
			{
				iFilledCircle(bx,by,3);
				iFilledCircle(dx,dy,3);
			}
			else if(val<4)
			{
				iFilledCircle(bx,by,3);
				iFilledCircle(dx,dy,3);
				iFilledCircle(bx-dis1,by-dis1,4);
				iFilledCircle(dx+dis1,dy-dis1,4);
			}
			else if(val<6)
			{
				iFilledCircle(bx,by,3);
				iFilledCircle(dx,dy,3);
				iFilledCircle(bx-dis1,by-dis1,4);
				iFilledCircle(dx+dis1,dy-dis1,4);
				iFilledCircle(bx-dis2,by-dis2,5);
				iFilledCircle(dx+dis2,dy-dis2,5);
			}
			else {
				val =0;
				bx =-1,dx=-1,by=-1,dy=-1;
				blood=0;
				
				random();
				score_flag=1;//from here score can increase again. 
			}
		}
		//********Enough saving the day**********
		if(life <=0 )
		{
			iSetColor(237,28,36);
			iFilledRectangle(0,0,1068,628);
			printf("score  %d   %d choto high\n",score,choto_high); 
			if(score>choto_high){
				player[2].score= score;
				iSetColor(255,255,255);
				//iShowBMP(634,91,"images//2.bmp");
				iShowBMP(0,0,"images//name.bmp");
				drawTextBox();
				mode=1;
				iSetColor(255,255,255);
				iText(410, 220, _name,GLUT_BITMAP_TIMES_ROMAN_24);

				iSetColor(255,255,255);
				//iText(500, 350, "Game over",GLUT_BITMAP_TIMES_ROMAN_24);
				iText(480,315,str,GLUT_BITMAP_TIMES_ROMAN_24);
				
			}
			//else new_window =1;
			//if(new_window==1){
			else{
				/*iSetColor(237,28,36);
				iFilledRectangle(0,0,1068,628);*/
				latest = -1;//game over
				iShowBMP(0,0,"images//game_over.bmp");
				iSetColor(255,255,255);
				//iText(500, 350, "Game over",GLUT_BITMAP_TIMES_ROMAN_24);
				iText(495,512,str,GLUT_BITMAP_TIMES_ROMAN_24);
				mode =0;
			}
		}
	}
}
	
 
void drawTextBox()
{
	iSetColor(150, 150, 150);
	iRectangle(405, 204, 250, 40);
}


void lelvel()
{
	if (score<=10) 
	{
		level = 1;
		iSetTimer(2000, random);
	}
	
	else if(score > 10 && score < 40 )
	{
		level = 2;
		iSetTimer(1500 , random);
	}
	else
	{
		level = 3;
		iSetTimer(1500 , random);
	}
}

void iMouseMove(int mx, int my)
{
    printf("iMOUSE move == %d  %d\n",mx,my);
}
void iMouse(int button, int state, int mx, int my)
{
	if(state == GLUT_DOWN || mx>0 ||mx<1068 ||my>0 ){
		printf("imouse emni == %d  %d\n",mx,my);
	}
	if(mx>mo && mx<(mo+100) && my>530 && my<581)
	{
		//doura =1;
		//PlaySound("musics\\crow_sound",NULL,SND_ASYNC);
		doura =1;
	}
	if(mx<=528 && my<=268 )
	{
		le_down = 1;
		le_up = 0;
		ri_down = 0;
		ri_up =0;
		lx = 470,ly = 80;
		show=1;
	}
	else if(mx<=528 && my>=268 ){
		le_down = 0;
		le_up = 1;
		ri_down = 0;
		ri_up =0;
		lx =470,ly=106;
		show=1;
	}
	else if(mx>=528 && my<=268 ){
		le_down = 0;
		le_up = 0;
		ri_down = 1;
		ri_up =0;
		lx =549,ly=80;
		show=1;
	}
	else if(mx>=528 && my>=268 ){
		le_down = 0;
		le_up = 0;
		ri_down = 0;
		ri_up =1;
		lx =549,ly=106;
		show=1;
	}
	if(button == GLUT_LEFT_BUTTON && state == GLUT_DOWN)
	{
		//for resume after pause 
		if(latest == 22)
		{
			//resume
			if(mx>74 && mx < 74+191 && my>520 && my <520+45 )  //as each box of the menu page is 191 X 45
			{
				latest = 3;
				return;
				//decrease_misfire();
			}
		}

		//for new-game(menu, latest == 2) or restart on pause and other options
		if(latest == -1)
		{
			if(mx>444 && mx < 444+195 && my>166 && my <166+49 )
			{
				latest = 3;
				//*****
				//_new();
				flag =1;
				
				on=2;
				mynew();
				high_kara();
				return;
			}

			if(mx>445 && mx < 445+195 && my>92 && my <92+49 )
			{
				latest = 2;
			}
		}
		

		if(latest  == 2)
		{
			foo = 0;
			//newgame and restart
			if(mx>70 && mx < 70+191 && my>455 && my <455+45 )  //as each box of the menu page is 191 X 45
			{
				latest = 3;
				//*****
				//_new();
				flag =1;
				
				on=2;
				mynew();
				high_kara();
				return;
				//latest = 3; // ei line mynew() call er  pore dile misfire 1 theke start hoy. tai aage dite hobe
			}
	//for other options(story,controls on main and pause page)
			if(mx>70 && mx < 70+191 && my>390 && my <390+45 )  //as each box of the menu page is 191 X 45
			{
				//mynew();
				latest = 4;
			}

			if(mx>70 && mx < 70+191 && my>195 && my <195+45 )  //as each box of the menu page is 191 X 45
			{
				//mynew();
				latest = 7;
			}

			if(mx>70 && mx < 70+191 && my>324 && my <324+45 )  //as each box of the menu page is 191 X 45
			{
				//mynew();
				latest = 5;
				_load();
				return;
			}

			if(mx>70 && mx < 70+191 && my>261 && my <261+45 )  //as each box of the menu page is 191 X 45
			{
				//mynew();
				latest = 6;
			}
			
			
			//hall of fame
			if(mx>70 && mx < 70+191 && my>129 && my <129+45 )  //as each box of the menu page is 191 X 45
			{
				latest = 29;
				dekha =1;
				high_kara();
				
			}
			//quit
			if(mx>70 && mx < 70+191 && my>64 && my <64+45 )  //as each box of the menu page is 191 X 45
			{
				exit(0);
			}
			
		}

		if(latest == 22)
		{
			foo = 1;
			//newgame and restart
			if(mx>70 && mx < 70+191 && my>455 && my <455+45 )  //as each box of the menu page is 191 X 45
			{
				latest = 3;
				//*****
				//_new();
				flag =1;
				high_kara();
				on=2;
				mynew();
				return;
				//latest = 3; // ei line mynew() call er  pore dile misfire 1 theke start hoy. tai aage dite hobe
			}
	//for other options(story,controls on main and pause page)
			if(mx>70 && mx < 70+191 && my>390 && my <390+45 )  //as each box of the menu page is 191 X 45
			{
				//mynew();
				latest = 4;
			}

			if(mx>70 && mx < 70+191 && my>195 && my <195+45 )  //as each box of the menu page is 191 X 45
			{
				//mynew();
				latest = 7;
			}

			if(mx>70 && mx < 70+191 && my>324 && my <324+45 )  //as each box of the menu page is 191 X 45
			{
				//mynew();
				latest = 5;
				_load();
				return;
			}

			if(mx>70 && mx < 70+191 && my>261 && my <261+45 )  //as each box of the menu page is 191 X 45
			{
				//mynew();
				latest = 6;
			}
			
			
			//hall of fame
			if(mx>70 && mx < 70+191 && my>129 && my <129+45 )  //as each box of the menu page is 191 X 45
			{
				latest = 29;
				dekha =1;
				high_kara();
				
			}
			//quit
			if(mx>70 && mx < 70+191 && my>64 && my <64+45 )  //as each box of the menu page is 191 X 45
			{
				exit(0);
			}
			
		}
	}
	if(latest == 3 || latest == 5)
	{
	
		if(button == GLUT_LEFT_BUTTON && state == GLUT_DOWN)
		{
			if(life > 0)
			{
				PlaySound("musics\\audio2",NULL,SND_ASYNC);
			}
			if(r == 2)
			{
				if(mx>234 && mx < 234+59 && my>442 && my <442+60 )
				{
					bx = 254,by=482,dx =274,dy = 482;
					blood =1;
					check = 1;
					k = 5; // civilian
					life = life - 1;
					//random();
				}
			}
			else if(r == 6)
			{
				if(mx>778  && mx <778+59  && my>442 && my < 442+60)
				{
					bx = 798,by=466,dx =818,dy = 466;
					//bx = 264,by=482,dx =280,dy = 482;
					blood =1;
					check = 1;
					k = 5;
					life = life - 1;
					//random();
				}

			}

			else  if(r == 7)

			{
				if(mx>911  && mx <911+59  && my>442 && my <442+60 )
				{
					bx = 931,by=467,dx = 951,dy = 467;
					blood =1;
					check = 1;
					k = 5;
					life = life - 1;
					//random();
				}

			}
			else  if(r == 12)
			{
				if(mx>373 && mx < 373+59 && my>442 && my < 442+60)
				{
                
					bx = 393,by=467,dx = 393+20,dy = 467;
					k = 5;
					blood =1;
					check = 1;
					life = life - 1;
				}
			}
			else  if(r == 18)
			{
				if(mx>778  && mx <778+59  && my>269 && my <269+60 )
				{
					bx = 798,by=294,dx = 798+20,dy = 294;
					blood =1;
					check = 1;
					k = 5;
					life = life - 1;
				}
			}

			else  if(r == 20)
			{
				if(mx>768 && mx <768+59   && my>91 && my <91 +60)
				{
					bx = 788,by=91+25,dx = 788+20,dy = 91+25;
					blood =1;
					check = 1;
					k = 5;
					life = life - 1;
				}
			}
			else if(r == 31)
			{
				if(mx>634  && mx < 634 +59 && my>91 && my < 91+60)
				{
                
					bx = 654,by=91+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					k = 5;
					life = life - 1;
				}
			}

			else if(r == 35)
			{
				if(mx>768  && mx <768 +59  && my>91 && my <91+60 )
				{
					bx = 788,by=91+25,dx = 788+20,dy = 91+25;
					blood =1;
					check = 1;
					k = 5;
					life = life - 1;
				}
			}

			else  if(r == 37)
			{
				if(mx>642 && mx <642 +59  && my>269 && my <269+60 )
				{
					bx = 662,by=269+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					k = 5;
					life = life - 1;
				}
			}
			else  if(r == 44)
			{
				if(mx>634  && mx < 634 +59  && my>91 && my <91+60 )
				{
					bx = 654,by=91+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					k = 5;
					life = life - 1;
					//random();
				}
			}
			else  if(r == 51)
			{
				if(mx>91  && mx <91 +59  && my>91 && my <91+60 )
				{
					bx = 111,by=91+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					k = 5;
					life = life - 1;
					//random();
				}
			}
			else  if(r == 57)
			{
				if(mx> 361 && mx < 361+59  && my> 91 && my <91+60 )
				{
					bx = 381,by=91+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					k = 5;
					life = life - 1;
					//random();
				}
			}

			else if(r == 58)
			{
				if(mx>361  && mx <361 +59  && my>91 && my <91 +60)
				{
					bx = 381,by=91+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					k = 5;
					life = life - 1;
					//random();
				}
			}
			else if(r == 59)
			{
				if(mx> 634 && mx <634+59   && my>91 && my <91+60 )
				{
					bx = 654,by=91+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					k = 5;
					life = life - 1;
					//random();
				}
			}
			else if(r == 60)
			{
				if(mx> 634 && mx <634 +59  && my>91 && my <91+60 )
				{
					bx = 654,by=91+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					k = 5;
					life = life - 1;
					//random();
				}
			}

			else if(r == 61)
			{
				if(mx>97  && mx <97+59   && my> 442 && my <442+60 )
				{
					bx = 117,by=442+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					k = 5;
					life = life - 1;
					//random();
				}
			}

	//villain start

			else if(r == 1 )
			{
				if(mx >97  && mx <97+59   && my > 442  && my <442+60 )
				{
					bx = 117,by=442+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
				}
				else
				{
					villain = villain + 1;
				}


			}
			else if(r == 3 )
			{
				if(mx > 373 && mx < 373+59    && my > 442 && my <442+60 )
				{
					bx = 393,by=442+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
				}
				else
				{
					villain = villain + 1;
				}

			}
			else if(r == 4 )
			{
				if(mx > 768 && mx <768 +59   && my > 91 && my <91+60 )
				{
					bx = 768+20,by=91+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
				}

				else
				{
					villain = villain + 1;
				}
			}
			else if(r == 5 )
			{
				if(mx > 642 && mx < 642+59   && my > 442 && my <442+60 )
				{
					bx = 642+20,by=442+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
				}
				else
				{
					villain = villain + 1;
				}

			}
			else if(r == 8 )
			{
				if(mx > 99 && mx <  99 +59 && my > 267 && my <267+60 )
				{
					bx = 99+20,by=267+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}
				else
				{
					villain = villain + 1;
				}

			}
			else if(r == 9 )
			{
				if(mx > 373 && mx <  373+59   && my >442  && my <442 +60)
				{
                
					bx = 373+20,by=442+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}
				else
				{
					villain = villain + 1;
				}

			}
			else if(r == 10 )
			{
				if(mx >236  && mx < 236 +59  && my >267  && my <267+60  )
				{
					bx = 236+20,by=267+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}
				else
				{
					villain = villain + 1;
				}

			}
			else if(r == 11)
			{
				if(mx > 361 && mx <361 +59   && my >91  && my <91+60  )
				{
					bx = 361+20,by=91+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}

				else
				{
					villain = villain + 1;
				}
			}
			else if(r == 13)
			{
				if(mx >642  && mx < 642+59   && my >442  && my <442+60 )
				{
					bx = 642+20,by=442+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}
				else
				{
					villain = villain + 1;
				}

			}
			else if(r == 14)
			{
				if(mx > 642 && mx < 642 +59  && my > 442 && my <442+60 )
				{
					bx = 642+20,by=442+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}
				else
				{
					villain = villain + 1;
				}

			}
			else if(r == 15 )
			{
				if(mx >642  && mx < 642+59   && my >269  && my <269 +60)
				{
					bx = 642+20,by=269+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}
				else
				{
					villain = villain + 1;
				}

			}
			else if(r == 16 )
			{
				if(mx >642  && mx <  642+59    && my > 269 && my <269+60 )
				{
					bx = 642+20,by=269+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();          
				}

				else
				{
					villain = villain + 1;
				}
			}
			else if(r == 17 )
			{
				if(mx >223  && mx <  223+59   && my > 91 && my <91+60 )
				{
					bx = 223+20,by=91+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}

				else
				{
					villain = villain + 1;
				}
			}
			else if(r == 19)
			{
				if(mx >  778 && mx <778 +59   && my >442  && my <442 +60)
				{
					bx = 778+20,by=442+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}
				else
				{
					villain = villain + 1;
				}

			}
			else if(r == 21 )
			{
				if(mx > 778 && mx <  778+59  && my >269  && my <269+60 )
				{
					bx = 778+20,by=269+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}
				else
				{
					villain = villain + 1;
				}

			}
			else if(r == 22 )
			{
				if(mx > 911 && mx <911 +59   && my > 442 && my < 442+60)
				{
					bx = 911+20,by=442+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}

				else
				{
					villain = villain + 1;
				}
			}
			else if(r == 23 )
			{
				if(mx >911  && mx <911 +59   && my >442  && my <442+60 )
				{
					bx = 911+20,by=442+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}
				else
				{
					villain = villain + 1;
				}

			}
			else if(r == 24 )
			{
				if(mx > 905 && mx < 905+59   && my > 91 && my <91+60 )
				{
					bx = 905+20,by=91+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}
				else
				{
					villain = villain + 1;
				}

			}
			else if(r == 25 )
			{
				if(mx > 911 && mx <911+59    && my > 269 && my < 269 +60 )
				{
					bx = 911+20,by=269+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}
				else
				{
					villain = villain + 1;
				}

			}
			else if(r == 26 )
			{
				if(mx > 99 && mx < 99 +59   && my > 267 && my <267 +60 )
				{
				   bx = 99+20,by=267+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}
				else
				{
					villain = villain + 1;
				}

			}
			else if(r == 27 )
			{
				if(mx > 905 && mx < 905 +59  && my >91  && my < 91+60)
				{
					bx = 905+20,by=91+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}
				else
				{
					villain = villain + 1;
				}

			}
			else if(r ==28 )
			{
				if(mx >905  && mx <  905+59  && my > 91 && my < 91+60)
				{
					bx = 905+20,by=91+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}
				else
				{
					villain = villain + 1;
				}

			}
			else if(r == 29 )
			{
				if(mx > 236 && mx <236  +59  && my >267  && my <267  )
				{
					bx = 236+20,by=267+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}

				else
				{
					villain = villain + 1;
				}
			}
			else if(r == 30 )
			{
				if(mx > 236 && mx <236  +59  && my > 267 && my <267+60  )
				{
					bx = 236+20,by=267+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}

				else
				{
					villain = villain + 1;
				}
			}
			else if(r ==32)
			{
				if(mx > 768 && mx < 768+59  && my >91  && my <91+60  )
				{
					bx = 768+20,by=91+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}

				else
				{
					villain = villain + 1;
				}
			}
			else if(r == 33 )
			{
				if(mx >  370 && mx <370 +59   && my >267  && my <267 +60)
				{
					bx = 370+20,by=267+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}

				else
				{
					villain = villain + 1;
				}
			}
			else if(r == 34 )
			{
				if(mx > 370 && mx < 370 +59  && my >267  && my < 267+60)
				{
				   bx = 370+20,by=267+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}
				else
				{
					villain = villain + 1;
				}

			}
			else if(r == 36 )
			{
				if(mx >370  && mx <370 +59   && my >267  && my < 267+60)
				{
					bx = 370+20,by=267+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}
				else
				{
					villain = villain + 1;
				}

			}
			else if(r ==38)
			{
				if(mx > 91 && mx <91  +59  && my >91  && my <91 +60 )
				{
					bx = 91+20,by=91+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}
				else
				{
					villain = villain + 1;
				}

			}
			else if(r == 39 )
			{
				if(mx > 642 && mx <  642+59    && my > 269 && my < 269+60)
				{
					bx = 642+20,by=269+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}
				else
				{
					villain = villain + 1;
				}

			}
			else if(r == 40 )
			{
				if(mx >642  && mx < 642  +59   && my > 269 && my <269+60 )
				{
					bx = 642+20,by=269+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}
				else
				{
					villain = villain + 1;
				}

			}
			else if(r == 41 )
			{
				if(mx >778  && mx <778 +59   && my > 269 && my <269+60 )
				{
					bx = 778+20,by=269+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}

				else
				{
					villain = villain + 1;
				}
			}
			else if(r == 42 )
			{
				if(mx >778  && mx <778 +59   && my > 269 && my < 269+60)
				{
					bx = 778+20,by=269+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}
				else
				{
					villain = villain + 1;
				}

			}
			else if(r == 43 )
			{
				if(mx > 778 && mx <778 +59   && my >269  && my < 269+60)
				{
					bx = 778+20,by=269+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}

				else
				{
					villain = villain + 1;
				}
			}
			else if(r == 45 )
			{
				if(mx > 223 && mx <  223 +59 && my >91  && my < 91+60 )
				{
					bx = 223+20,by=91+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}
				else
				{
					villain = villain + 1;
				}

			}
			else if(r ==46 )
			{
				if(mx > 911 && mx <911+59     && my > 269 && my <269 +60 )
				{
				   bx = 911+20,by=269+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}
				else
				{
					villain = villain + 1;
				}

			}
			else if(r == 47 )
			{
				if(mx >911  && mx <911  +59   && my >269  && my <269 +60 )
				{
					bx = 911+20,by=269+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}

				else
				{
					villain = villain + 1;
				}
			}
			else if(r == 48 )
			{
				if(mx >911  && mx < 911 +59   && my >269  && my <269 +60 )
				{
					bx = 911+20,by=269+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}
				else
				{
					villain = villain + 1;
				}

			}
			else if(r == 49 )
			{
				if(mx >91  && mx < 91+59   && my >91  && my <91+60 )
				{
				   bx = 91+20,by=91+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}
				else
				{
					villain = villain + 1;
				}

			}
			else if(r == 50 )
			{
				if(mx >91  && mx <91 +59   && my > 91 && my <91+60 )
				{
					bx = 91+20,by=91+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();;
				}
				else
				{
					villain = villain + 1;
				}

			}
			else if(r == 52 )
			{
				if(mx >91  && mx < 91+59   && my >91  && my < 91+60)
				{
					bx = 91+20,by=91+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}

				else
				{
					villain = villain + 1;
				}
			}
			else if(r == 53 )
			{
				if(mx >223  && mx <  223+59   && my > 91 && my <91+60 )
				{
					bx = 223+20,by=91+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}

				else
				{
					villain = villain + 1;
				}
			}
			else if(r == 54 )
			{
				if(mx > 223 && mx < 223 +59   && my >91  && my <91+60)
				{
					bx = 223+20,by=91+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}

				else
				{
					villain = villain + 1;
				}
			}
			else if(r == 55 )
			{
				if(mx > 223 && mx < 223+59   && my > 91 && my <91 +60)
				{
					bx = 223+20,by=91+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}
				else
				{
					villain = villain + 1;
				}

			}
			else if(r == 56 )
			{
				if(mx > 223 && mx < 223 +59  && my >91 && my <91+60 )
				{
					bx = 223+20,by=91+25,dx = bx+20,dy = by;
					blood =1;
					check = 1;
					if(score_flag==1){
						score = score + 1;
						score_flag =0;
					}
					k = 10;
					//random();
				}
				else
				{
					villain = villain + 1;
				}

			}

		}
		if(score>highscore) highscore = score;
	}

}
// mane hoilw k=10 hoile villain r k=5 hole civilian

/*
	function iKeyboard() is called whenever the user hits a key in keyboard.
	key- holds the ASCII value of the key pressed.
*/

void high_kara()
{
	fp =fopen("file/hall.txt","r+");
	if(!fp) printf("high kara e prblm");
	for(i=0;i<3;i++){
		fscanf(fp,"%d %s",&player[i].score,player[i].name);
		printf("%d %s\n",player[i].score,player[i].name);
	}
	choto_high = player[2].score;
	boro_high = player[0].score;
	highscore = boro_high;
	fclose(fp);
}
void high_era()
{
	for(j=3;j>0;j--){
		for(i=0;i<(j-1);i++){
			if(player[i].score < player[i+1].score){
				temp = player[i];
				player[i]=player[i+1];
				player[i+1] = temp;
			}
		}
	}
	
	fp = fopen("file/hall.txt","w+");
	if(!fp) printf("high era e prblm");
	for(i=0;i<3;i++){
		fprintf(fp,"%d %s\n",player[i].score,player[i].name);
		printf("%d %s\n",player[i].score,player[i].name);
	}
	fclose(fp);
}
void _new()
{
	fp =fopen("file/all.txt","r+");
	if(!fp) {
		printf("1st file e problem\n");
		//return 0;
	}
	fscanf(fp,"%d %d %d %d %d",&s,&h,&v,&li,&le);
	highscore =h;
	fclose(fp);
}
void _load()
{
	fp =fopen("file/all.txt","r");
	if(!fp) {
		printf("1st file e problem\n");
		//return 0;
	}
	fscanf(fp,"%d %d %d %d %d",&s,&h,&v,&li,&le);
	score =s;
	highscore =h;
	villain =v;
	life =li;
	level =le;
	//printf("%d %d %d %d %d\n",score,highscore,villain,life,level);
	//fprintf(fp,"%d %d %d %d %d",score,highscore,villain,life,level);
	fclose(fp);
}
void _save()
{
	fp= fopen("file/all.txt","w+");
	if(!fp) {
		printf("2nd file e problem\n");
		//return 0;
	}
	//printf("%d %d %d %d %d\n",score,highscore,villain,life,level);
	fprintf(fp,"%d %d %d %d %d",score,highscore,villain,life,level);
	fclose(fp);
}
void mynew()
{
	score = 0; 
	level = 1;
	villain = 0;
	life = 3;
	latest = 3;

}

void iKeyboard(unsigned char key)
{
	//*********Who is the Highscorer********
    if(mode == 1)
	{
        if(key == 32 || key == '\r')
		{
			latest =29;
			mode = 0;
			new_window =0;
			strcpy(player[2].name, _name);
			//printf("%s\n", palyer[2].name);
			high_era();
			high_kara();
			for(i = 0; i < len; i++)
			{
				_name[i] = 0;
			}
			len = 0;
		}
		else
		{
			_name[len] = key;
			len++;
		}
	}
	else{
		//***********new game***********
		if(key == 'n' )
		{
			mynew();
			high_kara;
			//_new();
			/*flag =1;
			high_kara();
			on=2;*/
		}
		//**********for saved game********
		if(key == 's')
		{
			_save();
			/*if(flag!=1){
				_load();
				high_kara();
				flag=1;
			}*/
			on=2;
		}
		//*********Hall of Fame********
		/*if(key == 'H'){
			dekha =1;
			high_kara();
		}*/
		if( key == 27)
		{
			_save();
			exit(0);
		}
		if(key == 'l')
		{	
			latest = 5;
			_load();
			//on=2;
		
		}
		if(latest == 1)
		{
			 if(key == 'c')
			{
				latest = 2;
			}
		}


		// did not find 'esc', so used 'q'

		if(latest == 3  || latest == 5)
		{
			if(key == 'p' )
			{
				latest = 22;
				foo = 1;
			}
		}

		if(latest == 4 || latest == 7 || latest == 6 || latest == 29)
		{
			if(key == 'b')
			{
				latest = 2;
			}
		}
		if(foo == 1 )
		{
			if(latest == 6  || latest == 7 || latest == 29 || latest == 4 || latest == 22)
			{
				if(key == 'r')
				{
						latest = 3;
						return;

						//decrease_misfire();
				
		
				}
			}
		}
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
	if( key == GLUT_KEY_END)
    {
        _save();
		exit(0);
	}
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

void random()
{
	//srand(time(NULL));
	r1 =rand();	
    r = ((r1%61) + 17*s2 + 31* s1 )%61;

	//printf("%d\n",r);
}

int main()
{
	time();
	//_file();
	//iSetTimer(2000,_file);
	iSetTimer(2000 , lelvel);
	iInitialize(1068,628,"Virtual Cop");
    return 0;
}