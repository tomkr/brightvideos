(ns brightvideos.votes
  (:require
    [reagent.core :as reagent :refer [atom cursor]]
    [devcards.core])
  (:require-macros [devcards.core :as dc :refer [defcard-rg]]))

; Upvote button
(defn upvote-button [score]
  [:a {:on-click #(swap! score inc)}
    [:i.fa.fa-plus.votes__plus]])

; Downvote button
(defn downvote-button [score]
  [:a {:on-click #(swap! score dec)}
    [:i.fa.fa-minus]])

; Voting control
(defn votes [score]
  [:div.votes
    [upvote-button score]
    [downvote-button score]
    [:div.votes__score @score]])

(defonce score (atom 0))

(defcard-rg votes
  "## Complete voting control"
  [votes score]
  score
  {:inspect-data true :history true})

(defcard-rg upvote-button
  "## A button for upvoting"
  [upvote-button score]
  score
  {:inspect-data true :history true})

(defcard-rg downvote-button
  "## A button for downvoting"
  [downvote-button score]
  score
  {:inspect-data true :history true})
