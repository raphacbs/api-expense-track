- Comandos kubernetes

`
kind create cluster --name=argocd
`
- Seta o contexto </br>
``kubectl cluster-info --context kind-argocd``

``kubectl get nodes``


``kubectl apply -f k8s\deployment.yaml``

``kubectl port-forward svc/expensetrackapi 8082:8082``