(ns brightvideos.core
  (:require
    [reagent.core :as reagent :refer [atom]]
    [devcards.core])
  (:require-macros [devcards.core :as dc :refer [defcard-rg]]))

; (enable-console-print!)

;; define your app data so that it doesn't get over-written on reload
(defonce app-state (atom {:videos [{
                                    :title "test" :score 10}]}))

; A single video item component
(defn video-item [video]
  [:li.video__item
    [:a {:href (:url video) :target "_blank"} (:title video)]])

; A list of video items
(defn video-list []
  [:div
    [:h1 "Brightvideos"]
    [:ul
      (for [video (:videos @app-state)]
        [video-item video])]])

(defn main []
  (if-let [node (.getElementById js/document "app")]
    (reagent/render-component [video-list]
      (. js/document (getElementById "app")))))

(main)

; Devcards
(defonce single-video-state (atom
  { :title "test"
    :url "http://www.youtube.com"
    :score 10 }))

(defcard-rg video-item
  "## A single video item"
  [video-item @single-video-state]
  single-video-state
  {:inspect-data true :history true })
