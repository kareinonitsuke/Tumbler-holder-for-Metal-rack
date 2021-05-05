(ns tumbler-holder.core
  (:require [scad-clj.scad :as scad]
            [scad-clj.model :as model]))

(def cup-hold-bottom-height 5)
(def cup-hold-out 45)
(def cup-hold-in 40)
(def cup-hold-height 10)

(def pipe-hold-out 17.5)
(def pipe-hold-in 11.75)
(def pipe-hold-wall-height 30)
(def pipe-hold-translate-x -20)


(def pipe-hold-deff-cube-a-x 10)
(def pipe-hold-deff-cube-a-y 30)
(def pipe-hold-deff-cube-a-z pipe-hold-wall-height)
;手で調整
(def pipe-hold-deff-cube-a-translate-x -40)
(def pipe-hold-deff-cube-a-translate-y -15)

(def pipe-hold-deff-cube-b-x 20)
(def pipe-hold-deff-cube-b-y 20)
(def pipe-hold-deff-cube-b-z pipe-hold-wall-height)
;手で調整
(def pipe-hold-deff-cube-b-translate-x -45)
(def pipe-hold-deff-cube-b-translate-y -10)

(def conect-cube-x 12)
(def conect-cube-y 20)
(def conect-cube-z cup-hold-height)
;手で調整
(def conect-cube-translate-x -7)


(def cup-hold
  (model/difference
    (model/cylinder cup-hold-out cup-hold-height :center false)
    (model/cylinder cup-hold-in cup-hold-height :center false))
)
(def pipe-cercle
  (model/translate [-20 0 0]
      (model/difference
        (model/cylinder pipe-hold-out pipe-hold-wall-height :center false)
        (model/cylinder pipe-hold-in pipe-hold-wall-height :center false)))
  )
(def pipe-deff
  (model/union
    (model/translate [pipe-hold-deff-cube-a-translate-x pipe-hold-deff-cube-a-translate-y 0](model/cube pipe-hold-deff-cube-a-x pipe-hold-deff-cube-a-y pipe-hold-deff-cube-a-z :center false))
    (model/translate [pipe-hold-deff-cube-b-translate-x pipe-hold-deff-cube-b-translate-y 0](model/cube pipe-hold-deff-cube-b-x pipe-hold-deff-cube-b-y pipe-hold-deff-cube-b-z :center false)))
)
(def pipe-hold
  (model/difference
    (model/translate [0 0 0] pipe-cercle )
    (model/translate [0 0 0] pipe-deff )
  )
)
(def connect-cube
  (model/cube conect-cube-x conect-cube-y conect-cube-z :center false)  
)


(def middle-parts
  (model/union
    (model/translate [cup-hold-out 0 0] cup-hold)
    (model/translate [0 0 0] pipe-hold)
    (model/translate [conect-cube-translate-x (- (/ conect-cube-y 2)) 0] connect-cube)
  )
)

;整理中
(def bottom-parts
  (model/union
    (model/translate [45 0 0]
      (model/difference
        (model/cylinder 45 pipe-hold-wall-height :center false)
        (model/translate [0 0 5](model/cylinder 40 pipe-hold-wall-height :center false))
        (model/translate [30 -10 5](model/cube 30 20 pipe-hold-wall-height :center false))))
    (model/translate [0 0 0] pipe-hold)
    (model/translate [-7 -10 0](model/cube 12 20 pipe-hold-wall-height :center false))
  )
)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (spit "middle-tumbler-holder.scad" (scad/write-scad middle-parts))
  (spit "bottom-tumbler-holder.scad" (scad/write-scad bottom-parts))
)