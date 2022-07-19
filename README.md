# Final Project – Easy Animator
---
In this project, an application is built to help to create simple but effective 2D animations from shapes. It draws an animation according to how it is described in the text, without being tied to how the description was produced. It is built in Model-View-Controller architecture.

The `main()` method is the entry point for this program. Users need to specify command-line arguments. The command-line arguments will be of the form

    -in "name-of-animation-file" -view "type-of-view" -out "where-output-show-go" -speed "integer-ticks-per-second"

# Model
---
The model represents an animation. It supports various kinds of 2D shapes. It also supports adding various kinds of animations to shapes.

## Model Interface

It contains all the operations that all the models should support. A model should be able to support adding shapes and adding animations. It also has some getters to get the information of this model.

## ModelImpl

This class implements Model Interface. The methods for building up a model are addShape, removeShape, and addAnimation. Four data structures are used to store the information. All the shapes are stored in a map. All the animations are also stored in a map. The animations sorted by starting time are stored in a list. The shapes status at each frame is stored in a list. Four getters are created to return the data structure accordingly. They help to create different views. Also, AnimationBuilder<T> Interface is implemented. It constructs any animation, shape-by-shape, and motion-by-motion from the input file.

## Shape subpackage

The interface and classes related to shape are collected in this subpackage.

- **Shape Interface:** This interface represents the shapes. It contains all the getters and setters that all the shapes should support. So the name, type, position, size, color, appear time, and disappear time of a shape can be gotten and set. An extra cloneShape method is used to help convert shape and animation information to frames.

- **AbstractShape:** This abstract class implements the getters and setters in interface.

- **Rectangle:** The rectangle concrete class overrides the toString method and cloneShape method.

- **Oval:** The oval concrete class overrides the toString method and cloneShape method.

- **ShapeType:** This class enumerates the shape types in this program, rectangle, and oval.

## Animation subpackage

The interface and classes related to animation are collected in this subpackage.

- **Animation Interface:** This interface represents the animations. It contains all the getters that all the animations should support.

- **AbstractAnimation:** This abstract class implements the getters in interface. An extra calculateTween method is bused to help get the interpolate status of a shape’s animation.

- **Move:** The move concrete class overrides the toString method, which helps make a text view. The currentPosition method is for the creation of visual view. The getters of starting and ending status are for the creation of the SVG view.

- **Scale:** The scale concrete class overrides the toString method, which helps make a text view. The currentPosition method is for the creation of visual view. The getters of starting and ending status are for the creation of the SVG view.

- **ChangeColor:** The change color concrete class overrides the toString method, which helps make a text view. The currentPosition method is for the creation of visual view. The getters of starting and ending status are for the creation of the SVG view.

- **AnimationType:** This class enumerates the animation types in this program, move, scale, and change color.

## Util subpackage

The utility classes are collected in this subpackage to make the code more organized.

- **Position:** This class represents the position of a shape. This point is denoted in Cartesian coordinates as (x,y).

- **Size:** This class represents the size of a shape.

- **Color:** This class represents the color of a shape. The color is in RGB format.

- **Canvas:** This class represents the Canvas. The position of the canvas is represented by the coordinates of the upper left corner. The dimension of the canvas is represented by the width and height.

# View
---
Four kinds of views are supported by this program.

1. Textual View: display the animation information in the console or save it in a .txt file

2. SVG View:  display the animation information in the console or save it in a .svg file

3. Visual View: display the animation using Java Swing GUI

4. Editor View: display the animation using Java Swing GUI, as well as provide features like start, pause, restart, loop, increase and decrease playing speed

This package contains a view interface, four concrete classes for each view respectively, and a VisualPanel class to facilitate visual view.

- **View Interface:** This is the interface of the view. It contains one method, displaying the user the interface.

- **TextualView:** This class converts the shape and animation information into a specified text format and displays it in the console or saves it as a text file.

- **SvgView:** This class converts the shape and animation information into a specified SVG format and displays it in the console or saves it as an SVG file. This file can be directly viewed in a web browser.

- **VisualView:** This class creates a visual view that can play through the animation once. A timer is used here to tell the window to keep refreshing itself automatically, so the GUI draws the shape frame by frame.

- **EditorView:** This class create a visual view that can play through the animation and support more features, including start, pause, restart, loop, increase and decrease playing speed. The features are performed by clicking the corresponding buttons (the actionPerformed method is implemented in the controller). The setListeners method is to set listeners to these buttons. The methods to control start, pause, restart, loop, increase and decrease playing speed features are created. When buttons of the loop, increase and decrease playing speed are clicked, an updated message will show up in the interface to tell the user what interaction has happened. This is to make the program more user-friendly.

- **VisualPanel:** This class extends JPanel and overrides the paintComponent method. It paints the shapes at a frame. This class facilitates visual view and editor view.

# Controller
---
The purpose of a controller is to mediate the interactions between the view and the model. In this program, there is a single controller.

- **Controller Interface:** This interface represents the controller. It contains all the operations that a controller should support. The run method will run the program. It will invoke different views (textual view, visual view, SVG view, or editor view) according to the arguments given by the user. The actionPerformed method is for editor view. Perform different actions according to the given action event made by a user. This arrangement ensures a looser coupling between the controller and view.

- **Controller:** This concrete class implements the interface. It invokes different views according to the -view argument given by users. In the editor view, it invokes different reactions according to the mouse click made by users.
