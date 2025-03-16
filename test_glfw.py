import glfw

if not glfw.init():
    print("Failed to initialize GLFW")
    exit()

window = glfw.create_window(640, 480, "Test Window", None, None)
if not window:
    print("Failed to create GLFW window")
    glfw.terminate()
    exit()

while not glfw.window_should_close(window):
    glfw.poll_events()

glfw.terminate()

