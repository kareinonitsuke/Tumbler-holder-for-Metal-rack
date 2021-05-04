(ns tumbler-holder.core
  (:require [scad-clj.scad :as scad]
            [scad-clj.model :as model]))

(def primitives
  (model/union
    (model/translate [45 0 0]
      (model/difference
        (model/cylinder 45 10 :center false)
        (model/cylinder 40 15 :center false)))
    (model/translate [0 0 0]
      (model/difference
        (model/translate [-20 0 0]
          (model/difference
            (model/cylinder 17.5 30 :center false)
            (model/cylinder 11.75 35 :center false)))
        (model/union
          (model/translate [-40 -15 0](model/cube 10 30 35 :center false))
          (model/translate [-45 -10 0](model/cube 20 20 35 :center false)))
      )
    )
    (model/translate [-7 -10 0](model/cube 12 20 10 :center false))
  )
)
(def bottom-primitives
  (model/union
    (model/translate [45 0 0]
      (model/difference
        (model/cylinder 45 30 :center false)
        (model/translate [0 0 5](model/cylinder 40 30 :center false))
        (model/translate [30 -10 5](model/cube 30 20 30 :center false))))
    (model/translate [0 0 0]
      (model/difference
        (model/translate [-20 0 0]
          (model/difference
            (model/cylinder 17.5 30 :center false)
            (model/cylinder 11.75 30 :center false)))
        (model/union
          (model/translate [-40 -15 0](model/cube 10 30 30 :center false))
          (model/translate [-45 -10 0](model/cube 20 20 30 :center false)))
      )
    )
    (model/translate [-7 -10 0](model/cube 12 20 30 :center false))
  )
)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (spit "tumbler-holder.scad" (scad/write-scad primitives))
  (spit "bottom-tumbler-holder.scad" (scad/write-scad bottom-primitives))

)