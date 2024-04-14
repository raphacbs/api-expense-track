- Comandos kubernetes

`
kind create cluster --name=argocd
`
- Seta o contexto </br>
``kubectl cluster-info --context kind-argocd``

``kubectl get nodes``


``kubectl apply -f k8s\deployment.yaml``

``kubectl port-forward svc/expensetrackapi 8082:8082``

`` kubectl port-forward svc/argocd-server -n argocd 8081:443``

``kubectl -n argocd get secret argocd-initial-admin-secret -o jsonpath="{.data.password}" | base64 -d``