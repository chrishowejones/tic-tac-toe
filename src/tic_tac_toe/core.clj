(ns tic-tac-toe.core)

(def example-board
  [[:o :x :o]
   [:x :o :x]
   [:x :o :x]])

(defn winning-line
  [line]
  (let [ls (set line)]
    (cond
      (some nil? ls) :ongoing
      (= 1 (count ls)) (first ls)
      :else :draw)))

(defn analyze [board]
  (let [cols-lines (-> board
                       (concat
                        (apply map vector board))
                       (conj
                        (map-indexed (fn [idx line] (nth line idx)) board)
                        (map-indexed (fn [idx line] (nth line idx)) (reverse board))))
        result (map winning-line cols-lines)]
    (cond
      (some #{:x :o} result) (some #{:x :o} result)
      (every? #(= :draw %) result) :draw
      :else :ongoing)))

(comment
  (assoc-in example-board [0 1] :o)
  (analyze example-board)
  (analyze (assoc-in example-board [0 1] :o))

  (map-indexed (fn [idx line] (nth line idx)) example-board)
  (map-indexed (fn [idx line] (nth line idx)) (reverse example-board))
  )


(defn fizzbuzz [n]
 (condp (fn [a b] (zero? (mod b a))) n
   15 "FizzBuzz"
   5 "Buzz"
   3 "Fizz"
   (str n)))

(comment
 (map fizzbuzz (range 1 21))
 )


(defn fizzbuzz2
  [n]
  (->> (mod n 15)
       #{0 3 5 6 9 10 12}
       {0 "FizzBuzz" 3 "Fizz" 5 "Buzz" 6 "Fizz" 9 "Fizz" 10 "Buzz" 12 "Fizz" nil (str n)}))

(comment
  (map fizzbuzz2 (range 1 21))

  (= (map fizzbuzz (range 1 21))
     (map fizzbuzz2 (range 1 21)))

  )
