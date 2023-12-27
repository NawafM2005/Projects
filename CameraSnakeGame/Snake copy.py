import math
import random
import cvzone
import cv2
import numpy as np
from cvzone.HandTrackingModule import HandDetector


cap = cv2.VideoCapture(1) # Create Webcam object with camera number
cap.set(3, 1280)  # Give it width of 1280 with prop id of 3
cap.set(4, 720)  # Give it height of 720 with prop id of 4

detector = HandDetector(detectionCon= 0.8, maxHands= 1) # Create detector object


class SnakeGame:

    def __init__(self, pathFood):
        self.points = []  # List of all points of the snake
        self.lengths = []  # distance between each point
        self.currentLength = 0  # Total length of the snake
        self.allowedLength = 150  # Total allowed length of the snake
        self.previousHead = 0, 0  # Previous head point

        self.imgFood = cv2.imread(pathFood, cv2.IMREAD_UNCHANGED)  # Create food and make transparent
        self.hFood, self.wFood, _ = self.imgFood.shape
        self.foodPoint = 0, 0
        self.randomFoodLocation()

        self.score = 0
        self.gameOver = False


    def randomFoodLocation(self):
        self.foodPoint = random.randint(100, 1000), random.randint(100, 600)

    def update(self, imgMain, currentHead):

        if self.gameOver:
            cvzone.putTextRect(imgMain, "Game Over", [300, 400], scale=7, thickness=5, offset=20)

            cvzone.putTextRect(imgMain, f"Your score: {self.score}", [300, 550], scale=7, thickness=5, offset=20)

        else:
            px, py = self.previousHead
            cx, cy = currentHead

            self.points.append([cx, cy]) # append current head points
            distance = math.hypot(cx - px, cy - py) # Find distance between prev and curr head
            self.lengths.append(distance)
            self.currentLength += distance
            self.previousHead = cx, cy

            # Length Reduction
            if self.currentLength > self.allowedLength:
                for i, length in enumerate(self.lengths):
                    self.currentLength -= length  # Reduce length
                    self.lengths.pop(i)
                    self.points.pop(i)  # Pop the values connected to length that was removed
                    if self.currentLength < self.allowedLength: # If snake becomes wanted size
                        break

            # Check if snake ate the food
            rx, ry = self.foodPoint
            if rx - self.wFood // 2 < cx < rx + self.wFood // 2 and ry - self.hFood // 2 < cy < ry + self.hFood // 2:
                self.randomFoodLocation()
                self.allowedLength += 50
                self.score += 1

            # Draw snake
            if self.points:
                for i, points in enumerate(self.points):
                    if i != 0:
                        cv2.line(imgMain, self.points[i - 1], self.points[i], (0, 0, 255), 20)
                        # Draw the snake at the points with colour and thickness
                    cv2.circle(imgMain, self.points[-1], 20, (200, 0, 200), cv2.FILLED)  # Draw circle

            # Check for collision
            pts = np.array(self.points[:-2], np.int32)
            pts = pts.reshape((-1, 1, 2))
            cv2.polylines(imgMain, [pts], False, (0, 200, 0), 3)
            min_dist = cv2.pointPolygonTest(pts, (cx, cy), True)

            if -2 <= min_dist <= 2:
                print('Hit')
                self.gameOver = True

                self.points = []  # List of all points of the snake
                self.lengths = []  # distance between each point
                self.currentLength = 0  # Total length of the snake
                self.allowedLength = 150  # Total allowed length of the snake
                self.previousHead = 0, 0  # Previous head point
                self.randomFoodLocation()

            # Draw Food
            rx, ry = self.foodPoint
            imgMain = cvzone.overlayPNG(imgMain, self.imgFood, (rx - self.wFood // 2, ry - self.hFood // 2))
            cvzone.putTextRect(imgMain, f"Your score: {self.score}", [50, 80], scale=3, thickness=3, offset=10)
        return imgMain


game = SnakeGame("Images/Donut.png")

while True:
    success, img = cap.read()
    img = cv2.flip(img, 1)  # Horizontal Flip
    hands, img = detector.findHands(img, flipType= False) # detect Hand and format

    if hands:
        lmList = hands[0]['lmList']  # get points
        pointIndex = lmList[8][0:2]  # Returns x y and ignore z
        img = game.update(img, pointIndex)
    cv2.imshow('Image', img)
    key = cv2.waitKey(1)
    if key == ord('r'):
        game.gameOver = False
        game.score = 0





