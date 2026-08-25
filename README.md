# GeoFort DevSecOps Assessment

This repository demonstrates a secure DevSecOps delivery workflow using:

- Java and Maven
- Jenkins CI/CD
- Docker
- Trivy vulnerability scanning
- Checkov IaC scanning
- Terraform
- Syft SBOM generation
- Cosign image signing and verification
- Kubernetes
- Kyverno admission control

The project includes application source code, CI/CD configuration, Kubernetes manifests, security policies, infrastructure-as-code examples, and security evidence.

---

## Overview

The purpose of this assessment is to demonstrate how security can be integrated throughout the software delivery lifecycle instead of being performed only at the end of deployment.

The workflow builds and validates a Java application, scans the application and container image, analyzes infrastructure-as-code, generates a software bill of materials, signs the resulting container image, verifies the signature, and applies Kubernetes admission controls.

The implementation is intended to be lightweight and repeatable. It is not presented as a complete production platform. Where controls are currently operating in reporting or audit mode, the intended production enforcement approach is documented.

---

## Repository Structure

```text
geofort-devsecops-assessment/
├── app/
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/
├── ci-cd/
│   └── Jenkinsfile
├── kubernetes/
├── policies/
├── terraform/
├── evidence/
├── documentation/
└── README.md
