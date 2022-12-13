
// Try this:
//
// Left blind up/my/down
// curl -H "Authorization: Bearer [token]" https://api.particle.io/v1/devices/[deviceid]/left_up -d params=x
// curl -H "Authorization: Bearer [token]" https://api.particle.io/v1/devices/[deviceid]/left_my -d params=x
// curl -H "Authorization: Bearer [token]" https://api.particle.io/v1/devices/[deviceid]/left_down -d params=x
//
// Right blind up/my/down
// curl -H "Authorization: Bearer [token]" https://api.particle.io/v1/devices/[deviceid]/right_up -d params=x
// curl -H "Authorization: Bearer [token]" https://api.particle.io/v1/devices/[deviceid]/right_my -d params=x
// curl -H "Authorization: Bearer [token]" https://api.particle.io/v1/devices/[deviceid]/right_down -d params=x

#include "MQTT.h"

int MOMENTARY_BUTTON_PRESS_MS = 900;
int MOMENTARY_PAUSE_MS = 100;

//----------------
// Pin Assignments
//----------------
int pin_left_up = D0;
int pin_left_my = D1;
int pin_left_down = D2;
int pin_right_up = D3;
int pin_right_my = D4;
int pin_right_down = D5;

//----------------
// MQTT
//----------------

char myIpString[24];
byte server[] = { 10, 5, 22, 2 };

void mqttCallback(char* topic, byte* payload, unsigned int length) {
    char p[length + 1];
    memcpy(p, payload, length);
    p[length] = NULL;
    String message(p);

    if (message.equals("left_up")) {
        doLeftUp("");
    }
    else if (message.equals("left_my")) {
        doLeftMy("");
    }
    else if (message.equals("left_down")) {
        doLeftDown("");
    }
    else if (message.equals("right_up")) {
        doRightUp("");
    }
    else if (message.equals("right_my")) {
        doRightMy("");
    }
    else if (message.equals("right_down")) {
        doRightDown("");
    }
    else if (message.equals("both_up")) {
        doBothUp("");
    }
    else if (message.equals("both_my")) {
        doBothMy("");
    }
    else if (message.equals("both_down")) {
        doBothDown("");
    }
}

MQTT mqttClient(server, 1883, mqttCallback);
int mqtt_status = 0;

bool setupMqtt() {
    Particle.variable("mqttstatus", mqtt_status);

    // connect to the server
    if (mqttClient.connect("helen")) {
        // subscribe
        mqttClient.subscribe("devices/particle/helen/command");
        return true;
    }
    return false;
}

void loopMqtt() {
    mqtt_status = mqttClient.isConnected() ? 1 : 0;

    if (!mqttClient.loop()) {
        // Not connected, try to reconnect
        if (!setupMqtt()) {
            // Reconnect failed, wait a few seconds
            delay(5000);
        }
    }
}

//-------------------
// MAIN PROGRAM
//-------------------

uint32_t lastReset = 0;

void setup() {
    pinMode(pin_left_up, OUTPUT);
    pinMode(pin_left_my, OUTPUT);
    pinMode(pin_left_down, OUTPUT);
    pinMode(pin_right_up, OUTPUT);
    pinMode(pin_right_my, OUTPUT);
    pinMode(pin_right_down, OUTPUT);

    Particle.function("left_up", doLeftUp);
    Particle.function("left_my", doLeftMy);
    Particle.function("left_down", doLeftDown);
    Particle.function("right_up", doRightUp);
    Particle.function("right_my", doRightMy);
    Particle.function("right_down", doRightDown);
    Particle.function("both_up", doBothUp);
    Particle.function("both_my", doBothMy);
    Particle.function("both_down", doBothDown);

    clearAll();

    lastReset = millis();

    setupMqtt();
}

void loop() {
    loopMqtt();

    // Reset every 24 hours
    if (millis() - lastReset > 24 * 60 * 60 * 1000UL) {
        System.reset();
    }
}

void clearAll() {
    digitalWrite(pin_left_up, LOW);
    digitalWrite(pin_left_my, LOW);
    digitalWrite(pin_left_down, LOW);
    digitalWrite(pin_right_up, LOW);
    digitalWrite(pin_right_my, LOW);
    digitalWrite(pin_right_down, LOW);
}

int pressMomentarily(int pin) {
    clearAll();
    digitalWrite(pin, HIGH);
    delay(MOMENTARY_BUTTON_PRESS_MS);
    digitalWrite(pin, LOW);
    delay(MOMENTARY_PAUSE_MS);
    return 1;
}

int doLeftUp(String command) {
    return pressMomentarily(pin_left_up);
}

int doLeftMy(String command) {
    return pressMomentarily(pin_left_my);
}

int doLeftDown(String command) {
    return pressMomentarily(pin_left_down);
}

int doRightUp(String command) {
    return pressMomentarily(pin_right_up);
}

int doRightMy(String command) {
    return pressMomentarily(pin_right_my);
}

int doRightDown(String command) {
    return pressMomentarily(pin_right_down);
}

int doBothUp(String command) {
    return pressMomentarily(pin_left_up)
        + pressMomentarily(pin_right_up);
}

int doBothMy(String command) {
    return pressMomentarily(pin_left_my)
        + pressMomentarily(pin_right_my);
}

int doBothDown(String command) {
    return pressMomentarily(pin_left_down)
        + pressMomentarily(pin_right_down);
}
