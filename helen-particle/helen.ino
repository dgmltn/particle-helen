
// Try this:
//
// Left blind up/my/down
// curl -H "Authorization: Bearer [token]" https://api.spark.io/v1/devices/[deviceid]/left_up -d params=x
// curl -H "Authorization: Bearer [token]" https://api.spark.io/v1/devices/[deviceid]/left_my -d params=x
// curl -H "Authorization: Bearer [token]" https://api.spark.io/v1/devices/[deviceid]/left_down -d params=x
//
// Right blind up/my/down
// curl -H "Authorization: Bearer [token]" https://api.spark.io/v1/devices/[deviceid]/right_up -d params=x
// curl -H "Authorization: Bearer [token]" https://api.spark.io/v1/devices/[deviceid]/right_my -d params=x
// curl -H "Authorization: Bearer [token]" https://api.spark.io/v1/devices/[deviceid]/right_down -d params=x

int MOMENTARY_BUTTON_PRESS_MS = 500;

////////////////////////////////////////////////////////////////////////////////////////////////////
// Pin Assignments
////////////////////////////////////////////////////////////////////////////////////////////////////
int pin_left_up = D0;
int pin_left_my = D1;
int pin_left_down = D2;
int pin_right_up = D3;
int pin_right_my = D4;
int pin_right_down = D5;

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

    clearAll();
}

void loop() {
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
