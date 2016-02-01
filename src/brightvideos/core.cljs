(ns brightvideos.core
  (:require [reagent.core :as reagent :refer [atom]]))

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:videos [{
                                    :title "test" :score 10}]}))

(defn video-item [video]
  [:li
    [:span (:title video)]])

(defn video-list []
  [:div
    [:h1 "Brightvideos"]
    [:ul
      (for [video (:videos @app-state)]
        [video-item video])]])

(reagent/render-component [video-list]
                          (. js/document (getElementById "app")))


(defn on-js-reload [])
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
