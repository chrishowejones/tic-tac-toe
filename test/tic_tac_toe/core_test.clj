(ns tic-tac-toe.core-test
  (:require [clojure.test :refer [deftest is testing]]
            [tic-tac-toe.core :refer :all]))

(deftest winning-line-found
  (is (= :x (winning-line [:x :x :x])))
  (is (= :o (winning-line [:o :o :o]))))

(deftest draw-found
  (is (= :draw (winning-line [:x :o :x])))
  (is (= :draw (winning-line [:o :o :x]))))

(deftest incomplete-line
  (is (= :ongoing (winning-line [nil :o :x])))
  (is (= :ongoing (winning-line [nil nil nil]))))

(def incomplete-board
  [[:o nil nil]
   [:x nil nil]
   [:o nil nil]])

(deftest ongoing-game
  (is (= :ongoing (analyze incomplete-board))))

(def drawn-board
  [[:o :x :x]
   [:x :o :o]
   [:o :x :x]])

(deftest drawn-game
  (is (= :draw (analyze drawn-board))))

(def x-win-horizontal
  [[:o :x :o]
   [:x :x :x]
   [:x :o :o]])

(def x-win-vertical
  [[:o :x :x]
   [:x :x :o]
   [:o :x :o]])

(def x-win-diagonal-back
  [[:o :o :x]
   [:x :x :o]
   [:x :x :o]])

(def x-win-diagonal-forward
  [[:x :o :o]
   [:x :x :o]
   [:o :x :x]])

(deftest x-wins
  (is (= :x (analyze x-win-vertical)))
  (is (= :x (analyze x-win-horizontal)))
  (is (= :x (analyze x-win-diagonal-back)))
  (is (= :x (analyze x-win-diagonal-forward))))

(defn swap-x-and-o
  [line]
  (mapv (fn [sym] (condp = sym
                    :x :o
                    :o :x
                    sym))
        line))

(deftest o-wins
  (is (= :o (analyze (mapv swap-x-and-o x-win-vertical))))
  (is (= :o (analyze (mapv swap-x-and-o x-win-horizontal))))
  (is (= :o (analyze (mapv swap-x-and-o x-win-diagonal-back))))
  (is (= :o (analyze (mapv swap-x-and-o x-win-diagonal-forward)))))
