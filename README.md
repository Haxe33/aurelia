# Aurelia - AppDev Project 

This Repository consists of the code for an Android Application, we have
developed for our course App Development. It is developed using Jetpack Compose.

## TOC

* [Used sources](#used-sources)
* [Info](#info)
* [Usage](#usage)
* [Folder Structure](#folder-structure)

## Used sources

The links to our sources we used can be found in the `app/src/main/res/raw/used resources.txt`.
Also inline reference is provided in form of comments and the correspondings tags to the links.

## Info

Three students, united by a shared fascination with zodiac signs and transcendences, embarked on a 
creative journey to develop this app for our App Development project. 
Our passion for the mystical and the celestial led us to explore the intricacies of astrology, 
inspiring us to create a tool that makes astrology accessible and engaging for everyone. 
This app is designed to be a gateway for exploring your zodiac sign, understanding your personality,
and uncovering the potential influences of celestial bodies on your life.

One key aspect of astrology explored in our app is the Ascendant, or rising sign. 
This is the zodiac sign that was rising on the eastern horizon at the time of your birth. 
It symbolizes your outer self, representing the way you project yourself to the world and how 
others perceive you initially. By integrating this feature, our app provides a more personalized 
astrological experience, offering insights into not only your sun sign but also how your Ascendant 
influences your life and interactions.

This app is an university project, made by Laura Hofer, Paul Kowatsch and Florian Hager at 
the university Klagenfurt, and is not intended to be used for professional astrological readings. 
It is meant to be a fun and engaging tool for exploring astrology and learning more about yourself 
and your zodiac sign. We hope you enjoy using it and that it inspires you to delve deeper 
into the mysteries of the cosmos!

## Usage

When using the Application for the first Time on a certain device you will always need to register 
an account. This can be done via the Registration Page, which can be reached by pressing the
`Registration` Button upon visiting the Launcher Page. There you can chose a username, a password as
well as select your zodiac sign.

From the Login Page it is also possible to reach the Info Page, where you can find some information 
about this project, as well as our motivation for developing it.

After Login / Registration you can dive into the astrological world. You will have the chance to get
a short description of each zodiac sign, as well as randomized horoscopes. Also it is possible to 
calculate your ascendant as well your compatibility with your significant other.

## Folder Structure

### `main/java/com/example/aurelia`

* contains Scripts and Classes which are mainly concerned with UI

#### `./logic`

* Contains the files which are used for providing logic to the Application
* Composition of Classes and Scripts

#### `./model`

* Contains the DAO for data persistence

### `main/res/raw`

* contains all the provided text examples the app contains

#### `./used resources.txt`
* contains the sources used throughout the development phase

