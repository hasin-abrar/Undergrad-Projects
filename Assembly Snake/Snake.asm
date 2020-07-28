.Model Small
draw_row Macro x
    Local l1
; draws a line in row x from col 10 to col 300
    MOV AH, 0CH
    MOV AL, 1; CYAN
    MOV CX, 10
    MOV DX, x
L1: INT 10h
    INC CX
    CMP CX, 301
    JL L1
    EndM

draw_col Macro y
    Local l2
    ; draws a line col y from row 20 to row 189
    MOV AH, 0CH
    MOV AL, 1; CYAN
    MOV CX, y
    MOV DX, 20
L2: INT 10h
    INC DX
    CMP DX, 190
    JL L2
    EndM

.Stack 100h
.Data
new_timer_vec   dw  ?,? ; timinig 
old_timer_vec   dw  ?,?
timer_flag  db  0
vel_x       dw  3
vel_y       dw  0
ARR_X DW 100,100,100,100,100,100,100,100,10000 DUP(?);100,100,100,100,100,500 DUP(?)    snake row
ARR_Y DW 48,45,42,39,36,33,30,27,10000 DUP(?);24,21,18,15,12,500 DUP(?)         snake col
;ARR_X DW 60 dup(0)
;ARR_Y DW 60 DUP(0)
CNT DW ?
LEN DW 8
;;;;;;;;;;;;;;;;;;KEYBOARD;;;;;;;;;;;
NEW_KEY_VEC DW ?,?  ;
OLD_KEY_VEC DW ?,?
SCAN_CODE DB 0
KEY_FLAG DB 0
UP_ARROW=72
DOWN_ARROW=80
LEFT_ARROW=75
RIGHT_ARROW=77
ESC_KEY=1
NO_UP DB 0
NO_DOWN DB 0
NO_LEFT DB 1
NO_RIGHT DB 0

FOODX1 DW 114
FOODY1 DW 94
FOODX2 DW 118
FOODY2 DW 98
LEN_PER_TIME DW 5
LEN_INC DW 2

SPEED DW 3

RAND_NUMX DW 168
RAND_NUMY DW 128
DIVISORX DW 137
DIVISORY DW 213

TEMPX DW ?
TEMPY DW ?
GAME_OVER_TEXT DB 'GAME OVER!  '
SCORE_TEXT DB 'SCORE :   $'   ;COUNT = 9  
SCORE_VALUE DB ?,'   $' ;550 LINE
COUNT DW ?
COL DW ?
ROW DW ?

SCO DW 0
CONGRATZ DB 'CONGRATULATION! HIGH SCORE!   $'
GREAT DW 0

HIGHFILE DB 'HS.TXT',0
HIGHSCORE DW 0
HIGH_VALUE DB 15 DUP(' ')
STORE_SCORE DB 15 DUP(' ')
HIGH_TEXT DB 'HIGHSCORE :   $'
HANDLE DW ?
;the file need to be in the BIN folder in the installation directory
.Code

set_display_mode Proc
; sets display mode and draws boundary
    MOV AH, 0
    MOV AL, 04h; 320x200 4 color
    INT 10h
; select palette    
    ;MOV AH, 0BH
   ; MOV BH, 1;;;;;;;;;;;;;;0;;;;;;;;;;;;;;;;;;;;;;;;;;;;
   ;MOV BL, 1
   ;INT 10h
; set bgd color
    MOV BH, 1
    MOV BL, 1; CYAN_(EIKANE NA TO!!!)
    INT 10h
; draw boundary
    draw_row 20
    draw_row 189
    draw_col 10
    draw_col 300
    
    RET
set_display_mode EndP

SET_CURSOR PROC
    PUSH AX
    XOR AX,AX
    MOV AH,2    ;SET CURSOR
    MOV BH,0    ;PAGE 0
    PUSH BX
    MOV BX,ROW
    MOV DH,BL    ;ROW 0
    POP BX
    PUSH BX
    MOV BX,COL
    MOV DL,BL   ;COL 
    POP BX
    INT 10H
    POP AX
    RET
SET_CURSOR ENDP

WRITE_LINE PROC
    ;SET SI TO STRING
    ;SET COUNT TO NUMBER OF LETTERS+1
    ;SET ROW
    ;SET COL
    PUSH AX
    CLD
    PUSH BX
    XOR BX,BX
    MOV AH,9    ;WRITE CHAR FUNCTION
    DO:
        DEC COUNT
        CMP COUNT,0
        JE COMPLETE
        LODSB
        MOV BL,3    ;RED COLOR
        MOV CX,1    ;WRITE COUNT CHAR
        INT 10H
        
        PUSH AX
        PUSH BX
        PUSH DX
        XOR BX,BX
        XOR AX,AX
        MOV AH,2    ;SET CURSOR
        MOV BH,0    ;PAGE 0
        PUSH BX
        MOV BX,ROW
        MOV DH,BL    ;ROW 0
        POP BX
        INC COL
        PUSH BX
        MOV BX,COL
        MOV DL,BL   ;COL 
        POP BX
        INT 10H
        
        POP DX
        POP BX
        POP AX
        
        JMP DO
    
    COMPLETE:
    POP BX
    POP AX
    RET
WRITE_LINE ENDP

OUTDEC PROC    
    ;INPUT IS IN AX
    ;OUTPUT TO DI POINTED DESTINATION
    PUSH AX
    PUSH BX
    PUSH CX
    PUSH DX   
   
    
    @END_IF1:
    XOR CX,CX  ;CX COUNTS DIGITS
    MOV BX,10D ;BX HAS DIVISOR
    
    @REPEAT1:
    XOR DX,DX
    DIV BX     ;AX = QUOTIENT DX = REMAINDER
    PUSH DX    ;SAVE REAMINDER ON STACK
    
    INC CX
    OR AX,AX   ;QUOTIENT = 0?
    JNE @REPEAT1
    
    @PRINT_LOOP:
    
        POP DX     ;DIGIT IN DL
        OR DL,30H  ;CONVERT TO CHARACTER
        MOV AL, DL
        STOSB      ;STORE THE CHARACTER IN AL TO DI POINTED DESTINATION 
        LOOP @PRINT_LOOP
    
    POP DX
    POP CX
    POP BX
    POP AX
    RET
OUTDEC ENDP

SET_CORD Proc
; displays ball at col CX and row DX with color given in AL
; input: AL = color of ball
;    CX = col
;    DX = row
    PUSH DX
    PUSH CX
    PUSH BX
    MOV BX,LEN
    MOV CNT,BX
    LEA DI,ARR_X
    LEA SI,ARR_Y
    DEC BX
    SHL BX,1
    ADD DI,BX
    ADD SI,BX
    POP BX 
    DRAW_CORD:
    
    CMP CNT,1
    JE RET_SET_CORD
    MOV DX,[DI-2]       ;last er ager tar row
    MOV CX,[SI-2]       ;last er ager tar col
    MOV [DI],DX         ;last er ta er ager ta ker follow korano
    MOV [SI],CX
    SUB SI,2
    SUB DI,2
    DEC CNT
    JMP DRAW_CORD
    RET_SET_CORD:
    POP CX      ;cx e notun mathar col
    POP DX      ;dx e notun mathar row
    LEA DI,ARR_X
    LEA SI,ARR_Y
    MOV [DI],DX
    MOV [SI],CX
    RET 
SET_CORD EndP

display_ball Proc
; displays ball at col CX and row DX with color given in AL
; input: AL = color of ball
;    CX = col
;    DX = row
    PUSH BX
    MOV BX,LEN
    MOV CNT,BX
    POP BX
    LEA DI,ARR_X
    LEA SI,ARR_Y
    DRAW:
    
    CMP CNT,0
    JE RET_DISPLAY_BALL
    MOV DX,[DI]
    MOV CX,[SI]
    MOV AH, 0CH ; write pixel
    INT 10h
    INC CX      ; pixel on next col
    INT 10h
    INC DX      ; down 1 row
    INT 10h
    DEC CX      ; prev col
    INT 10h
    DEC DX
    INT 10H    ; restore dx
    DEC CNT
    ADD SI,2
    ADD DI,2
    JMP DRAW
    RET_DISPLAY_BALL:
    RET 
display_ball EndP

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
DISPLAY_FOOD Proc
; displays ball at col CX and row DX with color given in AL
; input: AL = color of ball
;    CX = col
;    DX = row
    PUSH CX
    PUSH DX
    
    MOV DX,FOODX1
    MOV CX,FOODY1
    
          DRAW_FOOD:
        MOV AH, 0CH ; write pixel
        INT 10h
        INC CX      ; pixel on next col
        INT 10h
        INC CX      ; pixel on next col
        INT 10h
        INC CX      ; pixel on next col
        INT 10h
        INC CX      ; pixel on next col
        INT 10h
        INC DX      ; down 1 row
        INT 10h
        INC DX      ; down 1 row
        INT 10h
        INC DX      ; down 1 row
        INT 10h
        INC DX      ; down 1 row
        INT 10h
        DEC CX      ; prev col
        INT 10h
        DEC CX      ; prev col
        INT 10h
        DEC CX      ; prev col
        INT 10h
        DEC CX      ; prev col
        INT 10h
        DEC DX
        INT 10H      ; restore dx
        DEC DX
        INT 10H      ; restore dx
        DEC DX
        INT 10H      ; restore dx
        DEC DX
        ;INT 10H      ; restore dx
        
   RETURNING_DISPLAY_FOOD:      
     POP DX
     POP CX
     RET 
     
    ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;A L T E R;;;;;;;;;;;;;;;;;;;;;;;;;;
    ;PUSH CX
    ;PUSH DX
    
    ;PUSH BX
    ;MOV BX,4
    ;MOV CNT,BX
    
    ;MOV CX,FOODY1
    ;DEC CX
    
    ;MOV AH, 0CH ; write pixel
    ;INT 10h
    ;DRAW_FOOD_COL:
        ;CMP CNT,0
        ;JE RETURNING_DISPLAY_FOOD
        ;MOV DX,FOODX1
        ;INC CX
        ;DEC CNT
        ;MOV BX,4
        ;DRAW_FOOD_ROW:
            ;CMP BX,0
            ;JE DRAW_FOOD_COL
            
            ;INT 10H
            ;INC DX
            ;DEC BX
            ;JMP DRAW_FOOD_ROW
     ;RETURNING_DISPLAY_FOOD:      
         ;POP BX
         ;POP DX
         ;POP CX
         ;RET
         ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; A L T E R;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
DISPLAY_FOOD EndP

RANDOM_GEN PROC
    PUSH AX
    PUSH DX
    GEN_X:
        SUB DX,DX
        SUB AX,AX
        MOV AX,RAND_NUMX
        ADD AX,FOODX1
    
        DIV DIVISORX
        CMP DX,25
        JLE ADD_FOODX
        CMP DX,182
        JGE SUB_FOODX
        MOV FOODX1,DX
        ADD DX,4
        MOV FOODX2,DX
    GEN_Y:
        SUB DX,DX
        SUB AX,AX
        MOV AX,RAND_NUMY
        ADD AX,FOODY1
    
        DIV DIVISORY
        CMP DX,25
        JLE ADD_FOODY
        CMP DX,294
        JGE SUB_FOODY
        MOV FOODY1,DX
        ADD DX,4
        MOV FOODY2,DX
        JMP RETURNING_RANDOM_GEN
    ADD_FOODX:
        ADD DX,FOODX1
        MOV FOODX1,DX
        ADD DX,4
        MOV FOODX2,DX
        JMP GEN_Y
    SUB_FOODX:
        SUB DX,FOODX1
        MOV FOODX1,DX
        ADD DX,4
        MOV FOODX2,DX
        JMP GEN_Y
    ADD_FOODY:
        ADD DX,FOODY1
        MOV FOODY1,DX
        ADD DX,4
        MOV FOODY2,DX
        JMP RETURNING_RANDOM_GEN
    SUB_FOODY:
        SUB DX,FOODY1
        MOV FOODY1,DX
        ADD DX,4
        MOV FOODY2,DX
        JMP RETURNING_RANDOM_GEN
    RETURNING_RANDOM_GEN:
        ;MOV DX,RAND_NUMX
        ;ADD DX,DIVISORY
        ;MOV RAND_NUMX,DX
        
       ; MOV DX,RAND_NUMY
       ; ADD DX,DIVISORX
       ; MOV RAND_NUMY,DX
       POP DX
       POP AX
        
        RET
RANDOM_GEN ENDP
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
timer_tick Proc
    PUSH DS
    PUSH AX
    
    MOV AX, Seg timer_flag
    MOV DS, AX
    MOV timer_flag, 1
    
    POP AX
    POP DS
    
    IRET
timer_tick EndP
;;;;;;; KEYBOARD_INT;;;;;;;;;;;;;;;;;;;;
KEYBOARD_INT PROC
    PUSH DS
    PUSH AX
    
    MOV AX,SEG SCAN_CODE
    MOV DS,AX
 ;input scan code  
    IN AL,60H
    PUSH AX
    IN AL,61H
    MOV AH,AL
    OR AL,80H
    OUT 61H,AL
    XCHG AH,AL
    OUT 61H,AL
    POP AX
    MOV AH,AL
    TEST AL,80H
    JNE KEY_0
  ;make code
    MOV SCAN_CODE,AL
    MOV KEY_FLAG,1
    KEY_0:
        MOV AL,20H
        OUT 20H,AL
  ;restore register
    POP AX
    POP DS
    IRET
KEYBOARD_INT ENDP

SHOW_VAL PROC
    ;LEA DI,HELLO
    ;MOV AX,SCORE
    ;LEA SI,HELLO
     PUSH DI
     PUSH SI
     PUSH AX
     
     ;LEA DI,HELLO
     CLD
     ;MOV AX,SCORE
     CALL OUTDEC
     ;LEA SI,HELLO
     ;MOV COUNT,3
     ;MOV COL,5
     CALL WRITE_LINE
     POP AX
     POP SI
     POP DI
     RET
SHOW_VAL ENDP


move_ball Proc
; erase ball at current position and display at new position
; input: CX = col of ball position
;    DX = rwo of ball position
; erase ball
    MOV AL, 0
    CALL display_ball
; get new position
    LEA DI,ARR_X        ;DI/DX te row
    LEA SI,ARR_Y        ; SI/ CX e col
    MOV DX,VEL_Y
    MOV CX,VEL_X
    ADD CX,[SI]
    ADD DX, [DI]
    
    CALL check_boundary
    CALL SET_CORD
    CALL IF_SUICIDE
    ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
    CHECK_IF_EATEN:
        CMP DX,FOODX1
        JL DX2_CX2
        CMP DX,FOODX2
        JG DX2_CX2
        CMP CX,FOODY1
        JL DX2_CX2
        CMP CX,FOODY2
        JG DX2_CX2
        JMP EATEN
        
        DX2_CX2:
        MOV TEMPX,DX
        MOV TEMPY,CX
        INC DX
        INC CX
        CMP DX,FOODX1
        JL SOB_TIMER
        CMP DX,FOODX2
        JG SOB_TIMER
        CMP CX,FOODY1
        JL SOB_TIMER
        CMP CX,FOODY2
        JG SOB_TIMER
     SOB_TIMER:
        JMP TEST_TIMER
     EATEN:
         
     ;*************************************score kahani****************************
        
        
        PUSH AX
        MOV AX,@DATA
        MOV DS,AX
        MOV ES,AX
        POP AX
        
         ;INC SCO
        ADD SCO,5
        MOV ROW,1
        MOV COUNT,3
        MOV COL,9
        CALL SET_CURSOR
        LEA DI,SCORE_VALUE
        MOV AX,SCO
        LEA SI,SCORE_VALUE
        CLD
        CALL OUTDEC
        CALL WRITE_LINE
        ;;CALL SHOW_VAL
        ; UPDATE HIGHSCORE?
        PUSH DX
        
        MOV DX,SCO
        CMP DX,HIGHSCORE
        JLE CONT
        
        XCHG DX,HIGHSCORE
        MOV GREAT,1
        
        ;SHOW UPDATED HIGHSCORE
        LEA DI,HIGH_VALUE
        CLD
        MOV AX,HIGHSCORE
        CALL OUTDEC
        MOV COUNT,3
        MOV COL,36
        MOV ROW,1
        CALL SET_CURSOR
        LEA SI,HIGH_VALUE
        CALL WRITE_LINE
        CONT:
        POP DX    
        
        
        ;***********************************end score********************************** 
         MOV AL, 0
         CALL DISPLAY_FOOD
   
         CALLING_RANDOM_FUNC:
            CALL RANDOM_GEN
            
         CALLING_DISPLAY_FOOD:
            ;MOV CX,90
            ;MOV DX,90
            ;MOV FOODX1,DX
            ;MOV FOODY1,CX
            PUSH BX
            MOV BX,LEN_INC
            ADD LEN,BX
            POP BX
            MOV AL,2
            CALL DISPLAY_FOOD
    ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
     IF_LEN_INCREASE:    
         
         PUSH AX
         PUSH BX
         PUSH DX
         SUB DX,DX
         MOV AX,SCO
         MOV BX,LEN_PER_TIME
         DIV BX
         CMP DX,0
         JNE NO_INCREASING
         ;SHL LEN,1
         INC LEN_INC
         ;INC SPEED
         NO_INCREASING:
            POP DX
            POP BX
            POP AX
         
; check boundary
   ; CALL check_boundary;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; wait for 1 timer tick to display ball
test_timer:
    MOV DX,TEMPX
    MOV CX,TEMPY
    CMP timer_flag, 1
    JNE test_timer
    MOV timer_flag, 0
    MOV AL,3
    ;CALL SET_CORD
    CALL display_ball
    ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;#########################################################
    MOV AL,2
    CALL DISPLAY_FOOD
    ; CALL IF_SUICIDE
    RET 
move_ball EndP

IF_SUICIDE PROC
    PUSH DX
    PUSH CX
    LEA DI,ARR_X
    LEA SI,ARR_Y
    MOV DX,[DI]
    MOV CX,[SI]
    ADD DI,2    ;matha re khaoar chance nai
    ADD SI,2
    PUSH BX
    MOV BX,LEN
    MOV CNT,BX
    CHECK_IF_SUICIDE:
        CMP CNT,1
        JE RETURNING_IF_SUICIDE
        
        MOV BX,[DI]     ;
        INC BX
        MOV TEMPX,BX
        MOV BX,[SI]
        INC BX
        MOV TEMPY,BX
        
        CMP DX,[DI]
        JL IF_SUICIDE_DX2_CX2
        CMP DX,TEMPX
        JG IF_SUICIDE_DX2_CX2
        CMP CX,[SI]
        JL IF_SUICIDE_DX2_CX2
        CMP CX,TEMPY
        JG IF_SUICIDE_DX2_CX2
        JMP SUICIDE
        
        IF_SUICIDE_DX2_CX2:
            INC DX
            INC CX
            CMP DX,[DI]
            JL CHECK_IF_SUICIDE_NEXT
            CMP DX,TEMPX
            JG CHECK_IF_SUICIDE_NEXT
            CMP CX,[SI]
            JL CHECK_IF_SUICIDE_NEXT
            CMP CX,TEMPY
            JG CHECK_IF_SUICIDE_NEXT
            JMP SUICIDE
            
    CHECK_IF_SUICIDE_NEXT:
        DEC DX
        DEC CX
        ADD DI,2
        ADD SI,2
        DEC CNT
        JMP CHECK_IF_SUICIDE
    
    RETURNING_IF_SUICIDE:
        POP BX
        POP CX
        POP DX
        RET     ;not suicide return simple
    SUICIDE:
        POP BX
        POP CX
        POP DX
        CALL GAME_OVER
        ;CALL MAIN
       ; MOV AH,4CH
        ;INT 21H
        RET
            
IF_SUICIDE ENDP

check_boundary Proc
; determine if ball is outside screen, if so move it back in and 
; change ball direction
; input: CX = col of ball
;    DX = row of ball
; output: CX = valid col of ball
;     DX = valid row of ball
; check col value
    CMP CX, 11
    JG LP1
    CALL GAME_OVER 
LP1:    CMP CX, 298
    JL LP2
    CALL GAME_OVER
; check row value
LP2:    CMP DX, 21
    JG LP3
    CALL GAME_OVER
LP3:    CMP DX, 187
    JL done
    CALL GAME_OVER
done:
    RET 
check_boundary EndP

GAME_OVER PROC
    MOV AL,0
    CALL DISPLAY_BALL
    CALL DISPLAY_FOOD
    
    ;reset timer interrupt vector
    LEA DI,NEW_TIMER_VEC
    LEA SI,OLD_TIMER_VEC
    MOV AL,1CH
    CALL SETUP_INT
    
    ;reset keyboard interrupt vector
    LEA DI,NEW_KEY_VEC
    LEA SI,OLD_KEY_VEC
    MOV AL,9H
    CALL SETUP_INT
    
    ;**************EIKHANE LAST SCORE************
    PUSH AX
    MOV AX,@DATA
    MOV DS,AX
    MOV ES,AX
    POP AX
    
    PUSH DI
    PUSH SI
    PUSH AX
    
    MOV COUNT,12
    MOV COL,12
    MOV ROW,10
    CALL SET_CURSOR
    LEA SI,GAME_OVER_TEXT
    CALL WRITE_LINE
    
    XOR SI,SI
    XOR DI,DI
    MOV COUNT,8
    MOV COL,12
    MOV ROW,12
    CALL SET_CURSOR
    LEA SI,SCORE_TEXT
    CALL WRITE_LINE
    
    XOR SI,SI
    XOR DI,DI
    LEA DI,SCORE_VALUE
    CLD
    MOV AX,SCO
    CALL OUTDEC
    MOV COUNT,3
    MOV COL,19
    MOV ROW,12
    LEA SI,SCORE_VALUE
    CALL WRITE_LINE
    
    ;STORING THE HIGHSCORE IN FILE
    MOV AX,HIGHSCORE
    LEA DI,STORE_SCORE
    CLD
    CALL OUTDEC
    
    ;MOVE FILE POINTER TO START
    MOV BX, HANDLE ;GET HANDLE
    CALL MOV_PTER  ; MOVE POINTER

    MOV BX,HANDLE
    MOV CX,5
    LEA DX,STORE_SCORE
    CALL WRITE
    JC WRITE_ERROR
    JMP CHECK_CONGO
    
    WRITE_ERROR:
    MOV COUNT,13
    MOV ROW,12
    MOV COL,5
    CALL SET_COURSOR
    LEA SI,HIGH_TEXT
    CALL WRITE_LINE
    
    CHECK_CONGO:
    MOV AX,GREAT
    CMP AX,1
    JNE NORMAL
    ;DID A SAD JOB- HIGHSCORE!
    MOV COUNT,29
    MOV COL,6
    MOV ROW,8
    CALL SET_CURSOR
    LEA SI,CONGRATZ
    CALL WRITE_LINE
    
    NORMAL:
    MOV BX,HANDLE
    CALL CLOSE
    POP AX
    POP SI
    POP DI 
    
    ;read key
    MOV AH,0
    INT 16H
    
    ;reset to text mode
    MOV AH,0
    MOV AL,3
    INT 10H
    ;return to dos
    MOV AH,4CH
    INT 21H
    ;;;;;;;;;;;;;;;
    RET
GAME_OVER ENDP

setup_int Proc
; save old vector and set up new vector
; input: al = interrupt number
;    di = address of buffer for old vector
;    si = address of buffer containing new vector
; save old interrupt vector
    MOV AH, 35h ; get vector
    INT 21h
    MOV [DI], BX    ; save offset
    MOV [DI+2], ES  ; save segment
; setup new vector
    MOV DX, [SI]    ; dx has offset
    PUSH DS     ; save ds
    MOV DS, [SI+2]  ; ds has the segment number
    MOV AH, 25h ; set vector
    INT 21h
    POP DS
    RET
setup_int EndP

OPEN PROC
    ;DX FILENAME
    ;AL ACCESS CODE
    ;OUTPUT AX GETS HANDLE
    MOV AH,3DH
    MOV AL,2
    INT 21H
    
    RET
OPEN ENDP

WRITE PROC NEAR
    ;WRITES A FILE
    ;INPUT BX = HANDLE
    ;CX = BYTES TO WRITE
    ;DS:DX = DATA ADDRESS
    ;OUTPUT: AX = BYTES WRITTEN
    ;IF UNSUCCESSFUL CF =1, AX = ERROR CODE
    MOV AH,40H
    INT 21H
    RET

WRITE ENDP   

CLOSE PROC NEAR
    ;CLOSES A FILE
    ;INPUT BX = HANDLE
    ;OUTPUT CF =1; ERROR CODE IN AX
    MOV AH,3EH
    INT 21H
    RET
    
CLOSE ENDP

MOV_PTER PROC NEAR
    ;MOVES FILE POINTER TO EOF
    ;INPUT BX = FILE HANDLE
    ;OUTPUT: DX:AX = POINTER POSITION FROM BEGINNING
    
    MOV AH,42H
    XOR CX,CX
    XOR DX,DX
    MOV AL,0
    INT 21H
    RET 
    
MOV_PTER ENDP

READ PROC NEAR
    ;BX FILE HANDLE
    ;CX BYTES TO READ
    ;DX BUFFER ADDRESS
    ;AX NUMBER OF BYTES ACTUALLY READ
    PUSH CX
    MOV AH,3FH
    MOV CX,512
    INT 21H
    POP CX
    RET
READ ENDP

STORE_VALUE PROC
    ;SOURCE IN SI POINTED BUFFER
    ;OUTPUT AX HAS THE  TOTAL VALUE
    PUSH SI
    PUSH BX
    XOR BX,BX   ;BX HOLDS TOTAL
    XOR AX,AX
    LEA SI,HIGH_VALUE
    CLD
    CHECK:
        LODSB
        CMP AL,' '
        JE DONE_
        AND AX,000FH  ;DIGIT TO NUMBER
        PUSH AX
        MOV AX,10D
        MUL BX
        POP BX
        ADD BX,AX
        JMP CHECK
        DONE_:
        XOR AX,AX
        MOV AX, BX
    POP BX
    POP SI
    RET
STORE_VALUE ENDP

SET_COURSOR PROC
    PUSH AX
    XOR AX,AX
    MOV AH,2    ;SET CURSOR
    MOV BH,0    ;PAGE 0
    PUSH BX
    MOV BX,ROW
    MOV DH,BL    ;ROW 0
    POP BX
    PUSH BX
    MOV BX,COL
    MOV DL,BL   ;COL 
    POP BX
    INT 10H
    POP AX
    RET
SET_COURSOR ENDP

main Proc
    MOV AX, @data
    MOV DS, AX
    MOV ES,AX
    
    ; MOV CX, 298
    ;MOV DX, 100
    ;LEA DI,ARR_X
    ;LEA SI,ARR_Y
    ;MOV [DI],DX
    ;MOV [SI],CX
    
; set graphics display mode & draw border
    CALL set_display_mode
    
    ;****************WRITE SCORE AND HIGHSCORE*****************
    PUSH DI
    PUSH SI
    PUSH AX
    
    MOV COUNT,9
    MOV COL,1
    MOV ROW,1
    CALL SET_CURSOR
    LEA SI,SCORE_TEXT
    CALL WRITE_LINE
    ;HIGH SCORE
    MOV COUNT,12
    MOV COL,25
    MOV ROW,1
    CALL SET_CURSOR
    LEA SI,HIGH_TEXT
    CALL WRITE_LINE
    
    LEA DI,SCORE_VALUE
    CLD
    MOV AX,SCO
    CALL OUTDEC
    MOV COUNT,3
    MOV COL,9
    MOV ROW,1
    CALL SET_CURSOR
    LEA SI,SCORE_VALUE
    CALL WRITE_LINE
    
    ;**************OPEN FILE AND STORE HIGHSCORE***********
    
    LEA DX,HIGHFILE     ;file name
    MOV AL,2
    CALL OPEN           
    JC OPEN_ERROR
    MOV HANDLE,AX       
    
    LEA SI,HIGH_VALUE   ;source string for STORE_VALUE
    READ_LOOP:
        LEA DX,HIGH_VALUE   ;DESTINATION STRING
        MOV BX,HANDLE       
        CALL READ           ;HIGH_VALUE WILL HAVE THE STRING VALUE
        OR AX,AX            ;IF 0 BYTES READ CHECKING
        JE COMPLETE_
        MOV CX,AX   ;DOESN'T MATTER
        
        CALL STORE_VALUE
        ADD HIGHSCORE,AX
        JMP READ_LOOP
    OPEN_ERROR:
        ;JMP EXIT_    
        MOV COUNT,13
        MOV ROW,6
        MOV COL,5
        CALL SET_COURSOR
        LEA SI,HIGH_TEXT
        CALL WRITE_LINE
        MOV CX,0
        CALL STORE_VALUE
        ADD HIGHSCORE,2
COMPLETE_:
    LEA DI,HIGH_VALUE
    CLD
    MOV AX,HIGHSCORE
    CALL OUTDEC
    MOV COUNT,3
    MOV COL,36
    MOV ROW,1
    CALL SET_CURSOR
    LEA SI,HIGH_VALUE
    CALL WRITE_LINE
    
    POP AX
    POP SI
    POP DI
    
    ;***********************************************
; set up timer interrupt vector
    MOV new_timer_vec, offset timer_tick
    MOV new_timer_vec+2, CS
    MOV AL, 1CH; interrupt type
    LEA DI, old_timer_vec
    LEA SI, new_timer_vec
    CALL setup_int
    
; set up KEYBOARD interrupt vector
    MOV NEW_KEY_VEC,OFFSET KEYBOARD_INT
    MOV NEW_KEY_VEC+2,CS
    MOV AL,9H
    LEA DI,OLD_KEY_VEC
    LEA SI,NEW_KEY_VEC
    CALL SETUP_INT
; start ball at col = 298, row = 100
; for the rest of the program CX = ball row, DX = ball col
    ;MOV CX, 298
    ;MOV DX, 100
    MOV CX, 48
    MOV DX, 100
    LEA DI,ARR_X
    LEA SI,ARR_Y
    MOV [DI],DX
    MOV [SI],CX
    ;MOV DX,[DI]
    ;MOV CX,[SI]
    MOV AL, 3
    CALL display_ball
    MOV AL,2
    CALL DISPLAY_FOOD
    
TEST_KEY:
    CMP KEY_FLAG,1
    JNE TT_DUPLICATE
    MOV KEY_FLAG,0
    ;CMP SCAN_CODE,ESC_KEY
    ;JE DONE
    CMP SCAN_CODE,UP_ARROW
    JE UP
    CMP SCAN_CODE,DOWN_ARROW
    JE DOWN
    CMP SCAN_CODE,LEFT_ARROW
    JE LEFT
    CMP SCAN_CODE,RIGHT_ARROW
    JE RIGHT_DUPLICATE
    ;;;;;;;;;;;;;;;;;;;FAIZLAMI;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
    TT_DUPLICATE:
        JMP TT
    ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
    UP:
        CMP NO_UP,1
        JE TT_DUPLICATE
        
        PUSH BX
        MOV BX,SPEED
        NEG BX
        MOV VEL_Y,BX
        MOV VEL_X,0
        POP BX
        
        PUSH BX
        MOV BL,0
        MOV NO_LEFT,BL
        MOV NO_RIGHT,BL
        MOV BL,1
        MOV NO_DOWN,BL
        POP BX
        
        JMP tt
    RIGHT_DUPLICATE:
        JMP RIGHT
    DOWN:
        CMP NO_DOWN,1
        JE tt_D
        PUSH BX
        MOV BX,SPEED
        MOV VEL_Y,BX
        MOV VEL_X,0
        POP BX
        
        PUSH BX
        MOV BL,0
        MOV NO_LEFT,BL
        MOV NO_RIGHT,BL
        MOV BL,1
        MOV NO_UP,BL
        POP BX
        
        JMP tt
    tt_D:
        JMP TT
    LEFT:
        CMP NO_LEFT,1
        JE tt
        PUSH BX
        MOV BX,SPEED
        NEG BX
        MOV VEL_Y,0
        MOV VEL_X,BX
        POP BX
        
        PUSH BX
        MOV BL,0
        MOV NO_UP,BL
        MOV NO_DOWN,BL
        MOV BL,1
        MOV NO_RIGHT,BL
        POP BX
        
        JMP tt
    RIGHT:
        CMP NO_RIGHT,1
        JE tt
        PUSH BX
        MOV BX,SPEED
        MOV VEL_Y,0
        MOV VEL_X,BX
        POP BX
        
        PUSH BX
        MOV BL,0
        MOV NO_UP,BL
        MOV NO_DOWN,BL
        MOV BL,1
        MOV NO_LEFT,BL
        POP BX
        
        JMP tt  
        
    ;;;;;;;;;;;;;;;;;;;FAIZLAMI_2;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
    TEST_KEY_DUPLICATE:
        JMP TEST_KEY
    ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; wait for timer tick before moving the ball
tt:
    CMP timer_flag, 1
    JNE TEST_KEY_DUPLICATE
    MOV timer_flag, 0
    CALL move_ball
tt2:
    CMP timer_flag, 1
    JNE tt2
    MOV timer_flag, 0
    JMP TEST_KEY
main EndP
End main
