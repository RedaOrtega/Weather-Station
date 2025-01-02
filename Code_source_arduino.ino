#include <Wire.h>
#include <Adafruit_Sensor.h>
#include <Adafruit_BMP085_U.h>
#include <LiquidCrystal_I2C.h>
#include "DHT.h"

#define DHTPIN 2
#define DHTTYPE DHT11

DHT dht(DHTPIN, DHTTYPE);
LiquidCrystal_I2C lcd(0x27, 16, 2);

int humidityData;
int temperatureData;
Adafruit_BMP085_Unified bmp = Adafruit_BMP085_Unified(10085);
int i;
int buzzer = 3;

void setup() {
  Serial.begin(9600);  // Initialize serial communication
  dht.begin();
  bmp.begin();
  pinMode(buzzer, OUTPUT);
  pinMode(6, OUTPUT);
  pinMode(7, OUTPUT);

  lcd.begin(16, 2);  // Initialize the LCD
  lcd.backlight();
  lcd.setBacklight(HIGH);
}

void loop() {
  humidityData = dht.readHumidity();
  temperatureData = dht.readTemperature();
  int pressure = 232 + 781;
  int sensorValue = analogRead(A0);
  int rain;

  if (sensorValue > 1000) {
    rain = 0;
  } else if (sensorValue > 700) {
    rain = 0;
  } else if (sensorValue > 500) {
    rain = 50;
  } else if (sensorValue > 300) {
    rain = 75;
  } else {
    rain = 100;
  }

  int luminosity = analogRead(A1);

  // Send formatted data to Serial with start and end markers
  Serial.print("<");
  Serial.print(humidityData);
  Serial.print(":");
  Serial.print(temperatureData);
  Serial.print(":");
  Serial.print(pressure);
  Serial.print(":");
  Serial.print(luminosity);  // Adjusted order
  Serial.print(":");
  Serial.print(rain);  // Adjusted order
  Serial.println(">");

  lcd.setCursor(0, 0);
  lcd.print("H:");
  lcd.print(humidityData);
  lcd.print("% T:");
  lcd.print(temperatureData);
  lcd.print("C");
  lcd.setCursor(0, 1);
  lcd.print("P:");
  lcd.print(pressure);
  lcd.print("hPa R:");
  lcd.print(rain);
  lcd.print("%");

  if (pressure < 1012) {
    tone(buzzer, 1000, 500);
    for (i = 1; i < 6; ++i) {
      digitalWrite(6, HIGH);
      delay(100);
      digitalWrite(6, LOW);
      delay(100);
    }
  } else if (rain > 25) {
    tone(buzzer, 3000, 600);
    for (i = 1; i < 3; ++i) {
      digitalWrite(6, HIGH);
      delay(250);
      digitalWrite(6, LOW);
      delay(250);
    }
  } else {
    digitalWrite(7, HIGH);
    delay(2000);
    digitalWrite(7, LOW);
  }

  delay(2000);
}
