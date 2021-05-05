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

(def conect-cube-x 12)
(def conect-cube-y 20)
(def conect-cube-z cup-hold-height)


(def primitives
  (model/union
    (model/translate [cup-hold-out 0 0]
      (model/difference
        (model/cylinder cup-hold-out cup-hold-height :center false)
        (model/cylinder cup-hold-in cup-hold-height :center false)))
    (model/translate [0 0 0]
      (model/difference
        (model/translate [-20 0 0]
          (model/difference
            (model/cylinder pipe-hold-out pipe-hold-wall-height :center false)
            (model/cylinder pipe-hold-in pipe-hold-wall-height :center false)))
        (model/union
          (model/translate [-40 -15 0](model/cube 10 30 pipe-hold-wall-height :center false))
          (model/translate [-45 -10 0](model/cube 20 20 pipe-hold-wall-height :center false)))
      )
    )
    (model/translate [-7 (- (/ conect-cube-y 2)) 0](model/cube conect-cube-x conect-cube-y conect-cube-z :center false))
  )
)
(def bottom-primitives
  (model/union
    (model/translate [45 0 0]
      (model/difference
        (model/cylinder 45 pipe-hold-wall-height :center false)
        (model/translate [0 0 5](model/cylinder 40 pipe-hold-wall-height :center false))
        (model/translate [30 -10 5](model/cube 30 20 pipe-hold-wall-height :center false))))
    (model/translate [0 0 0]
      (model/difference
        (model/translate [-20 0 0]
          (model/difference
            (model/cylinder 17.5 pipe-hold-wall-height :center false)
            (model/cylinder 11.75 pipe-hold-wall-height :center false)))
        (model/union
          (model/translate [-40 -15 0](model/cube 10 30 pipe-hold-wall-height :center false))
          (model/translate [-45 -10 0](model/cube 20 20 pipe-hold-wall-height :center false)))
      )
    )
    (model/translate [-7 -10 0](model/cube 12 20 pipe-hold-wall-height :center false))
  )
)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (spit "tumbler-holder.scad" (scad/write-scad primitives))
  (spit "bottom-tumbler-holder.scad" (scad/write-scad bottom-primitives))
)