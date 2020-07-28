#include <AltSoftSerial.h>

#include <SoftwareSerial.h>
#include <TinyGPS.h>

#define START_MONITORING 1
#define TRACK_MY_RIDE 2
#define STOP_MONITORING 3

// Initialize software serial to communicate with GSM module
SoftwareSerial BluetoothSerial(2, 4);
AltSoftSerial GSMSerial;
String latlongtab[5];
String state,timegps,lon="142",lat="141",deviceId="CHECK";
int current_state = STOP_MONITORING;
double latitude, longitude,pastLon,pastLat;
String upCommand = "1";
void initializeLatLong(){
  int i = 0;
  for(i=1;i<=4;i++){
    latlongtab[i]="";
  }
}

void setup()
{
  GSMSerial.begin(9600);
  BluetoothSerial.begin(9600);
  Serial.begin(9600);
  Serial.println("START");
  initializeGPS();
  initializeGPRS();
}
void loop() {

  initializeLatLong();
  sendTabData("AT+CGNSINF",2000);
  if(state != 0){
//    Serial.println("State    : "+state);
//    Serial.println("Time     : "+timegps);
    Serial.println("latitude : "+lat);
    Serial.println("longitude: "+lon);
    
    double latitude = lat.toDouble();
    double longitude = lon.toDouble();
    if(latitude <5 || longitude < 5 || latitude >  180 || longitude >  180){
      Serial.println("INVALID");
    }else{
//      checkAndSendData();
      doNecessaryAction();
      pastLat = latitude;
      pastLon = longitude; 
    }
    
  }else{
    Serial.println("Gps initializing");
  }
//  sendToFirebase();
}


void doNecessaryAction(){
  switch(current_state){
    case START_MONITORING:
      current_state = startMonitoring();
      break;
    case TRACK_MY_RIDE:
      checkAndSendData();
      break;
    case STOP_MONITORING:
// Nothing needs to be done. Past values will be recorded below.
      break;
    default:
      break;
  }
  //check the DB to set the current_state value
  int new_state = checkIfStateChanged(current_state);
  if(new_state == STOP_MONITORING ){
//    Serial.println("STOP_MONITORING");
    current_state = STOP_MONITORING;
  }else if(new_state == START_MONITORING && current_state == STOP_MONITORING){
    Serial.println("START_MONITORING");
    current_state = START_MONITORING;
    
    int _count = 10;
    while(_count--){
      initializeLatLong();
      sendTabData("AT+CGNSINF",2000);
      if(state != 0){
//        Serial.println("State    : "+state);
//        Serial.println("Time     : "+timegps);
//        Serial.println("latitude : "+lat);
//        Serial.println("longitude: "+lon);
        latitude = lat.toDouble();
        longitude = lon.toDouble();
        if(latitude <5 || longitude < 5 || latitude >  180 || longitude >  180){
          Serial.println("INVALID");
        }else{
            Serial.println("latitude : "+lat);
            Serial.println("longitude: "+lon);
            pastLat = latitude;
            pastLon = longitude; 
            break;
        }
        
      }
    }
    
  }//else current_state = TRACK_MY_RIDE;
}


int checkIfStateChanged(int current_state){
  if (BluetoothSerial.available()) { // check if anything in UART buffer
    char data = BluetoothSerial.read();
    Serial.println("BT Available");
    Serial.println(data);
    if(data == '1'){ // did we receive this character?
      Serial.println("START_MONITORING");
       return START_MONITORING;
    }else if(data == '2'){
      Serial.println("STOP_MONITORING");
      return STOP_MONITORING;
      software_Reset();
    }
  }else{
//    Serial.println("Not Available");
    return current_state;
  }
}

int startMonitoring(){
  double diffLon = abs(pastLon - longitude);
  double diffLat = abs(pastLat - latitude);
  Serial.println("Difference : ");
  Serial.println(diffLon);
  Serial.println(diffLat);
  //differenc will be 0.03 or 0.02
  if(diffLon > 0.02 || diffLat > 0.02){
    Serial.println("SEND_ALERT");
    sendAlertToUser();  //SMS
    return TRACK_MY_RIDE;
  }else{
    return START_MONITORING;
  }
}


void sendAlertToUser(){
//  GSMSerial.flush();
//  Serial.println("\nSending SMS");
  GSMSerial.print("\r");
  delay(1000);                    //Wait for a second while the modem sends an "OK"
  GSMSerial.print("AT+CMGF=1\r");    //Because we want to send the SMS in text mode
  delay(1000);
  GSMSerial.print("AT+CMGS=\"+8801521496804\"\r");    //Start accepting the text for the message
  //to be sent to the number specified.
  //Replace this number with the target mobile number.
  delay(1000);
  GSMSerial.print("Someone is possibly stealing your ride ! Use \"Track My Ride\" to locate your ride instantly.\r");   //The text for the message
  delay(1000);
  GSMSerial.write(0x1A);  //Equivalent to sending Ctrl+Z 
  Serial.println("SMS Sent!\n");
}


void initializeGPS(){
  GSMSerial.println("AT+CGNSPWR=1");
  delay(1000);
  GSMSerial.println("AT+CGNSSEQ=RMC");
  delay(3000);
}

void checkAndSendData(){
  /*
  if(mySerial.available()){
    Serial.println(mySerial.read());
  }else{
    Serial.println("********************");
  } */
  unsigned long start = millis();
  while(millis() - start < 2000){
  }
  Serial.flush();
  sendToFirebase();
}

void initializeGPRS(){
  GSMSerial.println("AT+SAPBR=3,1,\"Contype\",\"GPRS\"");
  delay(1000);
  ShowSerialData();

//  Serial.println("AT+SAPBR=3,1,\"APN\",\"blweb\"");
  GSMSerial.println("AT+SAPBR=3,1,\"APN\",\"blweb\"");
  delay(1000);
  ShowSerialData();

//  Serial.println("AT+SAPBR=1,1");
  GSMSerial.println("AT+SAPBR=1,1");
  delay(1000);
  ShowSerialData();
  
  Serial.println("AT+HTTPINIT");
  GSMSerial.println("AT+HTTPINIT");
  delay(1000);
  ShowSerialData();
  
}

void software_Reset() // Restarts program from beginning but does not reset the peripherals and registers
{
asm volatile ("  jmp 0");  
} 

void sendToFirebase(){
  upCommand = "AT+HTTPPARA=\"URL\",\"http://sikdar.gwiddle.co.uk/firebaseTest2.php?deviceid="+deviceId+"&lat="+lat+"&lon="+lon+"\"";
//  Serial.println("AT+SAPBR=3,1,\"Contype\",\"GPRS\"");
  
  Serial.println("AT+HTTPPARA=\"CID\",1");
  GSMSerial.println("AT+HTTPPARA=\"CID\",1");
  delay(1000);
  ShowSerialData();
  
  Serial.println(upCommand);
  GSMSerial.println("AT+HTTPPARA=\"URL\",\"http://sikdar.gwiddle.co.uk/firebaseTest2.php?deviceid="+deviceId+"&lat="+lat+"&lon="+lon+"\"");
  delay(2000);
  ShowSerialData();
  
  Serial.println("AT+HTTPACTION=1");
  GSMSerial.println("AT+HTTPACTION=1");
  delay(4000);
  ShowSerialData();
  
  Serial.println("AT+HTTPREAD");
  GSMSerial.println("AT+HTTPREAD");
  delay(1000);
  
  PrintSerialData();
  
//  Serial.println("AT+HTTPTERM");
//  GSMSerial.println("AT+HTTPTERM");
//  delay(1000);
}

void ShowSerialData()
{
  while(GSMSerial.available()!=0){
    Serial.write(GSMSerial.read());
    GSMSerial.read();
  }
}

void PrintSerialData()
{
  while(GSMSerial.available()!=0){
    Serial.write(GSMSerial.read());
  }
}

void sendTabData(String command,const int timeout){

  GSMSerial.println(command);
  long int time = millis();
  int i =0;

  while((time+timeout)>millis()){

      while( GSMSerial.available() ){
        char c= GSMSerial.read();
        if( c!= ',' ){
          latlongtab[i]+=c;
          delay(100);
        }else{
          i++;
        }
        if( i ==5 ){
          delay(100);
          goto exitL;
        }
      }
  }exitL:
    state = latlongtab[1];
    timegps = latlongtab[2];
    lat = latlongtab[3];
    lon = latlongtab[4];
}
  
